package com.shopping.etrade.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.etrade.dto.CardCouponDTO;
import com.shopping.etrade.dto.CardDTO;
import com.shopping.etrade.dto.CardProductDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.enumtypes.CouponStatus;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.exception.ObjectNotFoundException;
import com.shopping.etrade.exception.SpendingIsNotEnoughException;
import com.shopping.etrade.model.Card;
import com.shopping.etrade.model.CardCoupon;
import com.shopping.etrade.model.CardProduct;
import com.shopping.etrade.model.Coupon;
import com.shopping.etrade.model.Product;
import com.shopping.etrade.model.base.Money;
import com.shopping.etrade.model.repository.CardCouponRepository;
import com.shopping.etrade.model.repository.CardProductRepository;
import com.shopping.etrade.model.repository.CardRepository;
import com.shopping.etrade.model.repository.CouponRepository;
import com.shopping.etrade.model.repository.ProductRepository;
import com.shopping.etrade.service.strategy.delivery.DeliveryStrategyContext;

@Service
@Transactional
public class CardCommandService {

	public final CardRepository cardRepository;
	public final CardProductRepository cardProductRepository;
	public final ProductRepository productRepository;
	public final CouponRepository couponRepository;
	public final CardCouponRepository cardCouponRepository;
	public final CampaignDiscountQueryService campaignDiscountQueryService;
	public final CardQueryService cardQueryService;
	public final DeliveryStrategyContext deliveryStrategyContext;
	Logger logger = LoggerFactory.getLogger(CardCommandService.class);
	@Autowired
	public CardCommandService(CardRepository cardRepository, CardProductRepository cardProductRepository,
			ProductRepository productRepository, CouponRepository couponRepository,
			CardCouponRepository cardCouponRepository, CampaignDiscountQueryService campaignDiscountQueryService,
			CardQueryService cardQueryService, DeliveryStrategyContext deliveryStrategyContext) {
		this.cardRepository = cardRepository;
		this.cardProductRepository = cardProductRepository;
		this.productRepository = productRepository;
		this.couponRepository = couponRepository;
		this.cardCouponRepository = cardCouponRepository;
		this.campaignDiscountQueryService = campaignDiscountQueryService;
		this.cardQueryService = cardQueryService;
		this.deliveryStrategyContext = deliveryStrategyContext;
	}

	public CardProductDTO addProductToCard(CardProductDTO cardProductDTO)
			throws IncompatibleCurrencyException, ObjectNotFoundException {
		CardDTO cardDTO = cardProductDTO.getCardDTO();
		// ayni urun sepete eklenmisse quantity update edilir.
		Card card = createOrGetCard(cardProductDTO, cardDTO);
		Product product = productRepository.findById(cardProductDTO.getProductDTO().getId())
				.orElseThrow(() -> new ObjectNotFoundException());
		CardProduct cardProduct = cardProductRepository.findByCardAndProduct(card, product);
		cardProduct = addQuantityOrBuyNewProduct(cardProductDTO, card, product, cardProduct);
		cardProduct = cardProductRepository.save(cardProduct);
		logger.info(cardProduct.getQuantity() + " adet " + product.getTitle() + " ürünü sepete ekleniyor....");
		Money.toMoneyDTO(product.getPrice()).multiply(cardProduct.getQuantity()).printAmount("Eklenen Ürünlerin Toplam Tutarı:");
		this.calculateAndSaveCardAmounts(card.getId());
		return CardProduct.toDTO(cardProduct);
	}

	public CardDTO calculateAndSaveCardAmounts(Long cardId)
			throws IncompatibleCurrencyException, ObjectNotFoundException {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new ObjectNotFoundException());
		MoneyDTO totalCardAmount = cardQueryService.getCardTotalAmountWithoutDiscount(card.getId());
		MoneyDTO totalCampaignDiscountedCardAmount = campaignDiscountQueryService
				.calculateDiscountedAmountOfCard(card.getId());
		MoneyDTO totalCampaignDiscountAmount = totalCardAmount.substractMoney(totalCampaignDiscountedCardAmount);
		MoneyDTO deliveryAmount = this.calculateDeliveryAmount(card.getId());
		card.setCampaignDiscount(Money.fromMoneyDTO(totalCampaignDiscountAmount));
		card.setBasketAmount(Money.fromMoneyDTO(totalCardAmount));
		card.setShippingAmount(Money.fromMoneyDTO(deliveryAmount));
		card = cardRepository.save(card);
		return Card.toDTO(card);
	}
	

	private CardProduct addQuantityOrBuyNewProduct(CardProductDTO cardProductDTO, Card card, Product product,
			CardProduct cardProduct) {
		if (cardProduct != null) {
			cardProduct.setQuantity(cardProduct.getQuantity() + cardProductDTO.getQuantity());
		} else {
			cardProduct = CardProduct.fromDTO(cardProductDTO);
			cardProduct.setCard(card);
			cardProduct.setProduct(product);
		}
		return cardProduct;
	}

	private Card createOrGetCard(CardProductDTO cardProductDTO, CardDTO cardDTO) throws ObjectNotFoundException {
		Card card;
		if (cardDTO == null) { // sepet yoksa yeni bir sepet yaratilir.
			card = new Card();
			card = cardRepository.save(card);
		} else {
			card = cardRepository.findById(cardProductDTO.getCardDTO().getId())
					.orElseThrow(() -> new ObjectNotFoundException());
		}
		return card;
	}

	public void removeProductFromCard(Long cardProductId, int quantity) throws ObjectNotFoundException, IncompatibleCurrencyException {
		CardProduct cardProduct = cardProductRepository.findById(cardProductId)
				.orElseThrow(() -> new ObjectNotFoundException());
		if (quantity >= cardProduct.getQuantity()) {
			cardProductRepository.delete(cardProduct);
		} else if(quantity < cardProduct.getQuantity()){
			int oldQuantity = cardProduct.getQuantity();
			cardProduct.setQuantity(oldQuantity-quantity);
			cardProductRepository.save(cardProduct);
		}
		logger.info(quantity + " adet " + cardProduct.getProduct().getTitle() + " ürünü sepetten çıkarılıyor....");
		Money.toMoneyDTO(cardProduct.getProduct().getPrice()).multiply(cardProduct.getQuantity()).printAmount("Çıkarılan Ürünlerin Toplam Tutarı:");
		this.calculateAndSaveCardAmounts(cardProduct.getCard().getId());
	}

	public CardCouponDTO addCouponToCard(Long cardId, String couponCode)
			throws IncompatibleCurrencyException, ObjectNotFoundException, SpendingIsNotEnoughException {
		Coupon coupon = couponRepository.findByCodeAndCouponStatus(couponCode, CouponStatus.ACTIVE);
		if (coupon == null) {
			throw new ObjectNotFoundException();
		}
		Money minCardAmount = coupon.getMinCardAmount();
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new ObjectNotFoundException());
		MoneyDTO cardTotalAmount = cardQueryService.getCardTotalAmountWithoutDiscount(cardId);
		if (Money.toMoneyDTO(minCardAmount).compareTo(cardTotalAmount) < 0) {
			CardCoupon cardCoupon = makeCouponTransaction(coupon, card);
			return CardCoupon.toDTO(cardCoupon);
		} else {
			throw new SpendingIsNotEnoughException();
		}
	}

	private CardCoupon makeCouponTransaction(Coupon coupon, Card card) throws IncompatibleCurrencyException {
		Money.toMoneyDTO(coupon.getDiscountAmount()).printAmount("Kupon indirimi uygulandı:");
		coupon.setCouponStatus(CouponStatus.USED);
		couponRepository.save(coupon);
		CardCoupon cardCoupon = CardCoupon.createCardCoupon(card, coupon);
		cardCoupon = cardCouponRepository.save(cardCoupon);
		if (card.getCouponDiscount() == null) {
			card.setCouponDiscount(coupon.getDiscountAmount());
		} else {
			MoneyDTO cardCouponDiscount = Money.toMoneyDTO(card.getCouponDiscount());
			MoneyDTO newCouponDiscount = Money.toMoneyDTO(coupon.getDiscountAmount());
			MoneyDTO totalCouponAmount = cardCouponDiscount.addMoney(newCouponDiscount);
			card.setCouponDiscount(Money.fromMoneyDTO(totalCouponAmount));
		}
		cardRepository.save(card);
		return cardCoupon;
	}


	public void removeCouponFromCard(Long cardCouponId) throws ObjectNotFoundException, IncompatibleCurrencyException {
		CardCoupon cardCoupon = cardCouponRepository.findById(cardCouponId)
				.orElseThrow(() -> new ObjectNotFoundException());
		if (cardCoupon != null) {
			Coupon coupon = cardCoupon.getCoupon();
			Money.toMoneyDTO(coupon.getDiscountAmount()).printAmount("Kupon indirimi kaldırıldı:");
			Card card = cardCoupon.getCard();
			cardCouponRepository.delete(cardCoupon);
			coupon.setCouponStatus(CouponStatus.ACTIVE);
			couponRepository.save(coupon);
			MoneyDTO couponDiscountDTO = Money.toMoneyDTO(card.getCouponDiscount());
			couponDiscountDTO = couponDiscountDTO.substractMoney(Money.toMoneyDTO(coupon.getDiscountAmount()));
			card.setCouponDiscount(Money.fromMoneyDTO(couponDiscountDTO));
			cardRepository.save(card);
		}
	}

	private MoneyDTO calculateDeliveryAmount(Long cardId)
			throws IncompatibleCurrencyException, ObjectNotFoundException {
		List<CardProductDTO> cardProductDTOList = cardQueryService.getCardProductListByCardId(cardId);
		int productCount = 0;
		HashMap<String, Integer> shippingMap = new HashMap<>();
		for (CardProductDTO cardProductDTO : cardProductDTOList) {
			String firmName = cardProductDTO.getProductDTO().getFirmName();
			int quantity = cardProductDTO.getQuantity();
			productCount = productCount + quantity;
			if (shippingMap.get(firmName) != null) {
				int totalProduct = shippingMap.get(firmName) + quantity;
				shippingMap.put(firmName, totalProduct);
			} else {
				shippingMap.put(firmName, quantity);
			}
		}
		int shippingCount = shippingMap.size();
		deliveryStrategyContext.setDeliveryStrategy(deliveryStrategyContext.calculateDeliveryStrategy(shippingCount));
		MoneyDTO deliveryAmount = deliveryStrategyContext.executeDeliveryStrategy(shippingCount);
		return deliveryAmount;
	}

}
