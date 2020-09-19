package com.shopping.etrade.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.etrade.dto.CardDTO;
import com.shopping.etrade.dto.CardProductDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.exception.ObjectNotFoundException;
import com.shopping.etrade.model.Card;
import com.shopping.etrade.model.CardProduct;
import com.shopping.etrade.model.base.Money;
import com.shopping.etrade.model.repository.CardProductRepository;
import com.shopping.etrade.model.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@Transactional(readOnly = true)
public class CardQueryService {
	Logger logger = LoggerFactory.getLogger(CardQueryService.class);
	public final CardRepository cardRepository;
	public final CardProductRepository cardProductRepository;

	@Autowired
	public CardQueryService(CardRepository cardRepository, CardProductRepository cardProductRepository) {
		this.cardRepository = cardRepository;
		this.cardProductRepository = cardProductRepository;
	}

	public CardDTO getCardById(Long cardId) throws ObjectNotFoundException {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new ObjectNotFoundException());
		return Card.toDTO(card);
	}
	
	public List<CardProductDTO> getCardProductListByCardId(Long cardId) throws ObjectNotFoundException {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new ObjectNotFoundException());
		List<CardProduct> cardProductList = cardProductRepository.findAllByCard(card);
		List<CardProductDTO> cardProductDTOList = new ArrayList<>();
		for (CardProduct cardProduct : cardProductList) {
			cardProductDTOList.add(CardProduct.toDTO(cardProduct));
		}
		return cardProductDTOList;
	}
	
	public MoneyDTO getCardTotalAmountWithoutDiscount(Long cardId) throws IncompatibleCurrencyException, ObjectNotFoundException {
		List<CardProductDTO> cardProductDTOList = this.getCardProductListByCardId(cardId);
		MoneyDTO totalCardAmount = new MoneyDTO(BigDecimal.ZERO, MoneyDTO.TL);
		for (CardProductDTO cardProductDTO2 : cardProductDTOList) {
			totalCardAmount = totalCardAmount.addMoney(cardProductDTO2.getTotalAmount());
		}
		return totalCardAmount;
	}
	
	public void printCardAmount(Long cardId) throws IncompatibleCurrencyException, ObjectNotFoundException {
		logger.info("*********************************************");
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new ObjectNotFoundException());
		MoneyDTO basketAmountDTO = Money.toMoneyDTO(card.getBasketAmount());
		basketAmountDTO.printAmount("İndirimsiz Toplam Tutar:");
		MoneyDTO campaignDiscountDTO = Money.toMoneyDTO(card.getCampaignDiscount());
		campaignDiscountDTO.printAmount("Toplam Kampanya İndirimi:");
		MoneyDTO couponDiscountDTO = Money.toMoneyDTO(card.getCouponDiscount());
		couponDiscountDTO.printAmount("Toplam Kupon İndirimi:");
		MoneyDTO shippingAmountDTO = Money.toMoneyDTO(card.getShippingAmount());
		shippingAmountDTO.printAmount("Toplam Kargolama Ücreti:");
		MoneyDTO totalPaymentAmountDTO = basketAmountDTO.substractMoney(couponDiscountDTO).addMoney(shippingAmountDTO).substractMoney(campaignDiscountDTO);
		logger.info("---------------------------------------------");
		totalPaymentAmountDTO.printAmount("Ödenecek Toplam Tutar:");
		logger.info("*********************************************");
	}
}
