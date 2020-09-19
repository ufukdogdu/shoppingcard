package com.shopping.etrade.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.shopping.etrade.dto.CardDTO;
import com.shopping.etrade.dto.CardProductDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.exception.ObjectNotFoundException;
import com.shopping.etrade.exception.SpendingIsNotEnoughException;
import com.shopping.etrade.mock.CardCouponMocker;
import com.shopping.etrade.mock.CardMocker;
import com.shopping.etrade.mock.CardProductMocker;
import com.shopping.etrade.mock.CouponMocker;
import com.shopping.etrade.mock.MoneyMocker;
import com.shopping.etrade.mock.ProductMocker;
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

public class CardCommandServiceTest {

	public CardRepository cardRepository;
	public CardProductRepository cardProductRepository;
	public ProductRepository productRepository;
	public CouponRepository couponRepository;
	public CardCouponRepository cardCouponRepository;
	public CampaignDiscountQueryService campaignDiscountQueryService;
	public CardQueryService cardQueryService;
	public DeliveryStrategyContext deliveryStrategyContext;
	public CardCommandService cardCommandService;

	@Before
	public void setup() {
		cardRepository = Mockito.mock(CardRepository.class);
		cardProductRepository = Mockito.mock(CardProductRepository.class);
		productRepository = Mockito.mock(ProductRepository.class);
		couponRepository = Mockito.mock(CouponRepository.class);
		cardCouponRepository = Mockito.mock(CardCouponRepository.class);
		campaignDiscountQueryService = Mockito.mock(CampaignDiscountQueryService.class);
		cardQueryService = Mockito.mock(CardQueryService.class);
		deliveryStrategyContext = Mockito.mock(DeliveryStrategyContext.class);
		cardCommandService = new CardCommandService(cardRepository, cardProductRepository, productRepository,
				couponRepository, cardCouponRepository, campaignDiscountQueryService, cardQueryService,
				deliveryStrategyContext);
	}

	@Test
	public void testRemoveProductFromCard() throws ObjectNotFoundException, IncompatibleCurrencyException {
		CardProduct mockCardProduct = CardProductMocker.generateCardProduct();
		Mockito.when(cardProductRepository.findById(1L)).thenReturn(Optional.of(mockCardProduct));
		Mockito.doNothing().when(cardProductRepository).delete(mockCardProduct);
		Card mockCard = CardMocker.genarateCard();
		Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(mockCard));
		Mockito.when(cardQueryService.getCardTotalAmountWithoutDiscount(1L)).thenReturn(MoneyMocker.generateMoneyDTO());
		Mockito.when(campaignDiscountQueryService.calculateDiscountedAmountOfCard(1L))
		.thenReturn(MoneyMocker.generateMoneyDTO());
		List<CardProductDTO> mockCardProductDTOList = CardProductMocker.generateCardProductDTOList();
		Mockito.when(cardQueryService.getCardProductListByCardId(1L)).thenReturn(mockCardProductDTOList);
		Mockito.when(deliveryStrategyContext.executeDeliveryStrategy(Mockito.anyInt()))
				.thenReturn(MoneyMocker.generateMoneyDTO());
		Mockito.when(cardRepository.save(Mockito.any())).thenReturn(mockCard);
		cardCommandService.removeProductFromCard(1L, 1);
	}	
	
	@Test
	public void testAddProductToCard() throws IncompatibleCurrencyException, ObjectNotFoundException {
		Card mockCard = CardMocker.genarateCard();
		Mockito.when(cardRepository.save(Mockito.any())).thenReturn(mockCard);
		Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(mockCard));
		Product mockProduct = ProductMocker.generateProductMock();
		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
		CardProduct mockCardProduct = CardProductMocker.generateCardProduct();
		Mockito.when(cardProductRepository.findByCardAndProduct(Mockito.any(), Mockito.any()))
				.thenReturn(mockCardProduct);
		Mockito.when(cardProductRepository.save(mockCardProduct)).thenReturn(mockCardProduct);
		Mockito.when(cardQueryService.getCardTotalAmountWithoutDiscount(1L)).thenReturn(MoneyMocker.generateMoneyDTO());
		Mockito.when(campaignDiscountQueryService.calculateDiscountedAmountOfCard(1L))
				.thenReturn(MoneyMocker.generateMoneyDTO());
		List<CardProductDTO> mockCardProductDTOList = CardProductMocker.generateCardProductDTOList();
		Mockito.when(cardQueryService.getCardProductListByCardId(1L)).thenReturn(mockCardProductDTOList);
		Mockito.when(deliveryStrategyContext.executeDeliveryStrategy(Mockito.anyInt()))
				.thenReturn(MoneyMocker.generateMoneyDTO());
		CardProductDTO cardProductDTO = CardProductMocker.generateCardProductDTO();
		cardProductDTO = cardCommandService.addProductToCard(cardProductDTO);
		assertThat(cardProductDTO).isNotNull();
	}

	@Test
	public void testAddCouponToCard()
			throws IncompatibleCurrencyException, ObjectNotFoundException, SpendingIsNotEnoughException {
		Coupon mockCoupon = CouponMocker.generateCoupon();
		Money minCardAmount = MoneyMocker.generateMoney();
		minCardAmount.setAmount(BigDecimal.ONE);
		mockCoupon.setMinCardAmount(minCardAmount);
		Mockito.when(couponRepository.findByCodeAndCouponStatus(Mockito.anyString(), Mockito.any()))
				.thenReturn(mockCoupon);
		Card mockCard = CardMocker.genarateCard();
		Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(mockCard));
		MoneyDTO mockUSD = MoneyMocker.generateMoneyDTO();
		mockUSD.setCurrency("USD");
		Mockito.when(cardQueryService.getCardTotalAmountWithoutDiscount(1L)).thenReturn(mockUSD);
		Mockito.when(couponRepository.save(Mockito.any())).thenReturn(mockCard);
		Mockito.when(cardCouponRepository.save(Mockito.any())).thenReturn(CardCouponMocker.generateCardCoupon());
		Mockito.when(cardRepository.save(Mockito.any())).thenReturn(mockCard);
		cardCommandService.addCouponToCard(1L, "couponCode");
	}

	@Test(expected = SpendingIsNotEnoughException.class)
	public void testAddCouponToCardSpendingException()
			throws IncompatibleCurrencyException, ObjectNotFoundException, SpendingIsNotEnoughException {
		Coupon mockCoupon = CouponMocker.generateCoupon();
		Mockito.when(couponRepository.findByCodeAndCouponStatus(Mockito.anyString(), Mockito.any()))
				.thenReturn(mockCoupon);
		Card mockCard = CardMocker.genarateCard();
		Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(mockCard));
		Mockito.when(cardQueryService.getCardTotalAmountWithoutDiscount(1L)).thenReturn(MoneyMocker.generateMoneyDTO());
		Mockito.when(couponRepository.save(Mockito.any())).thenReturn(mockCard);
		Mockito.when(cardCouponRepository.save(Mockito.any())).thenReturn(CardCouponMocker.generateCardCoupon());
		Mockito.when(cardRepository.save(Mockito.any())).thenReturn(mockCard);
		cardCommandService.addCouponToCard(1L, "couponCode");
	}

	@Test
	public void testRemoveCouponFormCard() throws ObjectNotFoundException, IncompatibleCurrencyException {
		CardCoupon mockCardCoupon = CardCouponMocker.generateCardCoupon();
		Mockito.when(cardCouponRepository.findById(1L)).thenReturn(Optional.of(mockCardCoupon));
		Mockito.doNothing().when(cardCouponRepository).delete(Mockito.any());
		Mockito.when(couponRepository.save(Mockito.any())).thenReturn(mockCardCoupon);
		Card mockCard = CardMocker.genarateCard();
		Mockito.when(cardRepository.save(mockCard)).thenReturn(mockCard);
		cardCommandService.removeCouponFromCard(1L);
	}

}
