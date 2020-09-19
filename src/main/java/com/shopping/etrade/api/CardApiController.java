package com.shopping.etrade.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.etrade.dto.CardCouponDTO;
import com.shopping.etrade.dto.CardDTO;
import com.shopping.etrade.dto.CardProductDTO;
import com.shopping.etrade.dto.ProductDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.exception.ObjectNotFoundException;
import com.shopping.etrade.exception.SpendingIsNotEnoughException;
import com.shopping.etrade.request.RequestAddProductToCard;
import com.shopping.etrade.response.ResponseAddProductToCard;
import com.shopping.etrade.service.CardCommandService;
import com.shopping.etrade.service.CardQueryService;

@RestController
@RequestMapping(value = "/shopping", produces = "application/json;charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
public class CardApiController {

	private final CardCommandService cardCommandService;
	private final CardQueryService cardQueryService;

	@Autowired
	public CardApiController(CardCommandService cardCommandService, CardQueryService cardQueryService) {
		this.cardCommandService = cardCommandService;
		this.cardQueryService = cardQueryService;
	}

	@PostMapping(value = "/add/product/to/card", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseAddProductToCard addProductToCard(@RequestBody @Valid RequestAddProductToCard request)
			throws IncompatibleCurrencyException, ObjectNotFoundException {
		ResponseAddProductToCard response = new ResponseAddProductToCard();
		response.setCardProductDTO(cardCommandService.addProductToCard(request.getCardProductDTO()));
		return response;
	}

	@PutMapping(value = "remove/product/from/card", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void removeProductFromCard(@RequestParam("CARD_PRODUCT_ID") Long cardProductId, @RequestParam("QUANTITY") int quantity)
			throws ObjectNotFoundException, IncompatibleCurrencyException {
		cardCommandService.removeProductFromCard(cardProductId, quantity);
	}

	@PutMapping(value = "add/coupon/to/card/{COUPON_CODE}", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public CardCouponDTO addCouponToCard(@PathVariable("COUPON_CODE") String code, @RequestParam("CARD_ID") Long cardId)
			throws IncompatibleCurrencyException, ObjectNotFoundException, SpendingIsNotEnoughException {
		return cardCommandService.addCouponToCard(cardId, code);
	}

	@PutMapping(value = "remove/coupon/from/card/{CARD_COUPON_ID}", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void removeCouponFromCard(@PathVariable("CARD_COUPON_ID") Long cardCouponId)
			throws IncompatibleCurrencyException, ObjectNotFoundException, SpendingIsNotEnoughException {
		cardCommandService.removeCouponFromCard(cardCouponId);
	}

	@PutMapping(value = "calculate/save/card/payment/amount", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public CardDTO calculateCardPaymentAmount(@RequestParam("CARD_ID") Long cardId)
			throws IncompatibleCurrencyException, ObjectNotFoundException {
		return cardCommandService.calculateAndSaveCardAmounts(cardId);
	}
	

	@GetMapping(value = "/get/card/{CARD_ID}/info", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public CardDTO getCardInfo(
			@PathVariable("CARD_ID") Long cardId) throws IncompatibleCurrencyException, ObjectNotFoundException {
		// sepet bilgilerini getirirken güncel kampanya ve kargo ücreti hesaplaması yapılarak getirilir.
		return cardCommandService.calculateAndSaveCardAmounts(cardId);
	}

	@PutMapping(value = "test", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void testApplication()
			throws IncompatibleCurrencyException, ObjectNotFoundException, SpendingIsNotEnoughException {
		CardProductDTO cardProductDTO = CardProductDTO.createMock(null, ProductDTO.createMock(1L), 2);
		cardProductDTO = cardCommandService.addProductToCard(cardProductDTO);
		cardProductDTO = CardProductDTO.createMock(CardDTO.createMock(cardProductDTO.getCardDTO().getId()),
				ProductDTO.createMock(2L), 3);
		cardProductDTO = cardCommandService.addProductToCard(cardProductDTO);
		cardProductDTO = CardProductDTO.createMock(CardDTO.createMock(cardProductDTO.getCardDTO().getId()),
				ProductDTO.createMock(3L), 6);
		cardProductDTO = cardCommandService.addProductToCard(cardProductDTO);
		cardProductDTO = CardProductDTO.createMock(CardDTO.createMock(cardProductDTO.getCardDTO().getId()),
				ProductDTO.createMock(4L), 4);
		cardProductDTO = cardCommandService.addProductToCard(cardProductDTO);
		cardCommandService.removeProductFromCard(cardProductDTO.getId(), 2);
		CardCouponDTO cardCouponDTO = cardCommandService.addCouponToCard(cardProductDTO.getCardDTO().getId(), "DEF1234");
		cardCommandService.removeCouponFromCard(cardCouponDTO.getId());
		cardCommandService.addCouponToCard(cardProductDTO.getCardDTO().getId(), "ABC32434");
		cardQueryService.printCardAmount(cardProductDTO.getCardDTO().getId());
	}

}
