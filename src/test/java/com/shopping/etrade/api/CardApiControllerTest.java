package com.shopping.etrade.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shopping.etrade.dto.CardCouponDTO;
import com.shopping.etrade.dto.CardDTO;
import com.shopping.etrade.dto.CardProductDTO;
import com.shopping.etrade.dto.ProductDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.exception.ObjectNotFoundException;
import com.shopping.etrade.exception.SpendingIsNotEnoughException;
import com.shopping.etrade.mock.AddProductToCardMocker;
import com.shopping.etrade.mock.CardCouponMocker;
import com.shopping.etrade.mock.CardMocker;
import com.shopping.etrade.mock.CardProductMocker;
import com.shopping.etrade.request.RequestAddProductToCard;
import com.shopping.etrade.response.ResponseAddProductToCard;
import com.shopping.etrade.service.CardCommandService;
import com.shopping.etrade.service.CardQueryService;

public class CardApiControllerTest {

	public CardCommandService cardCommandService;
	public CardQueryService cardQueryService;
	public CardApiController controller;
	
	@Before
	public void setUp() {
		cardCommandService = Mockito.mock(CardCommandService.class);
		cardQueryService = Mockito.mock(CardQueryService.class);
		controller = new CardApiController(cardCommandService, cardQueryService);
	}

	@Test
	public void testAddProductToCard() throws IncompatibleCurrencyException, ObjectNotFoundException {
		RequestAddProductToCard mockRequest = AddProductToCardMocker.generateAddProductToCardRequest();
		ResponseAddProductToCard mockResponse = AddProductToCardMocker.generateAddProductToCardResponse();
		Mockito.when(cardCommandService.addProductToCard(mockRequest.getCardProductDTO())).thenReturn(mockResponse.getCardProductDTO());
		ResponseAddProductToCard response =  controller.addProductToCard(mockRequest);
		assertThat(response.getCardProductDTO()).isEqualTo(mockResponse.getCardProductDTO());
	}

	@Test
	public void testRemoveProductFromCard() throws ObjectNotFoundException, IncompatibleCurrencyException {
		Mockito.doNothing().when(cardCommandService).removeProductFromCard(1L, 2);
		controller.removeProductFromCard(1L, 2);
	}
	
	@Test
	public void testAddCouponToCard() throws IncompatibleCurrencyException, ObjectNotFoundException, SpendingIsNotEnoughException {
		CardCouponDTO mockCardCouponDTO = CardCouponMocker.generateCardCouponDTO();
		Mockito.when(cardCommandService.addCouponToCard(1L, "code")).thenReturn(mockCardCouponDTO);
		CardCouponDTO dto = controller.addCouponToCard("code", 1L);
		assertThat(dto).isEqualTo(mockCardCouponDTO);
	}

	@Test
	public void testRemoveCouponFromCard() throws IncompatibleCurrencyException, ObjectNotFoundException, SpendingIsNotEnoughException {
		Mockito.doNothing().when(cardCommandService).removeCouponFromCard(1L);
		controller.removeCouponFromCard(1L);
	}
	
	@Test
	public void testCalculateCardPaymentAmount() throws IncompatibleCurrencyException, ObjectNotFoundException {
		CardDTO mockCardDTO = CardMocker.generateCardDTO();
		Mockito.when(cardCommandService.calculateAndSaveCardAmounts(1L)).thenReturn(mockCardDTO);
		CardDTO dto = controller.calculateCardPaymentAmount(1L);
		assertThat(dto).isEqualTo(mockCardDTO);
	}
	
	@Test
	public void testGetCardInfo() throws IncompatibleCurrencyException, ObjectNotFoundException {
		CardDTO mockCardDTO = CardMocker.generateCardDTO();
		Mockito.when(cardCommandService.calculateAndSaveCardAmounts(1L)).thenReturn(mockCardDTO);
		CardDTO dto = controller.getCardInfo(1L);
		assertThat(dto).isEqualTo(mockCardDTO);
	}
	
	@Test
	public void testTest() throws IncompatibleCurrencyException, ObjectNotFoundException, SpendingIsNotEnoughException {
		CardProductDTO mockCardProductDTO = CardProductMocker.generateCardProductDTO();
		Mockito.when(cardCommandService.addProductToCard(Mockito.any())).thenReturn(mockCardProductDTO);
		Mockito.doNothing().when(cardCommandService).removeProductFromCard(Mockito.anyLong(), Mockito.anyInt());
		CardCouponDTO mockCardCouponDTO = CardCouponMocker.generateCardCouponDTO();
		Mockito.when(cardCommandService.addCouponToCard(Mockito.anyLong(), Mockito.anyString())).thenReturn(mockCardCouponDTO);
		Mockito.doNothing().when(cardQueryService).printCardAmount(Mockito.anyLong());
		controller.testApplication();
	}
}
