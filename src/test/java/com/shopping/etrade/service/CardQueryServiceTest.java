package com.shopping.etrade.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.shopping.etrade.dto.CardProductDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.enumtypes.CouponStatus;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.exception.ObjectNotFoundException;
import com.shopping.etrade.exception.SpendingIsNotEnoughException;
import com.shopping.etrade.mock.CardMocker;
import com.shopping.etrade.mock.CardProductMocker;
import com.shopping.etrade.model.Card;
import com.shopping.etrade.model.CardCoupon;
import com.shopping.etrade.model.CardProduct;
import com.shopping.etrade.model.Coupon;
import com.shopping.etrade.model.base.Money;
import com.shopping.etrade.model.repository.CardProductRepository;
import com.shopping.etrade.model.repository.CardRepository;

public class CardQueryServiceTest {

	public CardRepository cardRepository;

	public CardProductRepository cardProductRepository;

	public CardQueryService cardQueryService;

	@Before
	public void setup()
	{
		cardRepository = Mockito.mock(CardRepository.class);
		cardProductRepository = Mockito.mock(CardProductRepository.class);
		cardQueryService = new CardQueryService(cardRepository, cardProductRepository);
	}

	@Test
	public void testGCardProductListByCardId() throws ObjectNotFoundException {
		Card mockCard = CardMocker.genarateCard();
		Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(mockCard));
		List<CardProduct> mockCardProductList = CardProductMocker.generateCardProductList();
		Mockito.when(cardProductRepository.findAllByCard(Mockito.any())).thenReturn(mockCardProductList);
		List<CardProductDTO> cardProductDTOList = cardQueryService.getCardProductListByCardId(1L);
		assertThat(cardProductDTOList).isNotNull();
		assertThat(cardProductDTOList.size()).isEqualTo(mockCardProductList.size());
	}
	
	@Test
	public void testGetCardTotalAmountWithoutDiscount() throws IncompatibleCurrencyException, ObjectNotFoundException {
		Card mockCard = CardMocker.genarateCard();
		Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(mockCard));
		List<CardProduct> mockCardProductList = CardProductMocker.generateCardProductList();
		Mockito.when(cardProductRepository.findAllByCard(Mockito.any())).thenReturn(mockCardProductList);
		MoneyDTO moneyDTO = cardQueryService.getCardTotalAmountWithoutDiscount(1L);
		assertThat(moneyDTO).isNotNull();
	}

	@Test
	public void testPrintCardAmount() throws IncompatibleCurrencyException, ObjectNotFoundException {
		Card mockCard = CardMocker.genarateCard();
		Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(mockCard));
		cardQueryService.printCardAmount(1L);
	}
	

	
}
