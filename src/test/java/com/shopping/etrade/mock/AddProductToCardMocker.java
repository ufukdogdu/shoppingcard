package com.shopping.etrade.mock;

import com.shopping.etrade.request.RequestAddProductToCard;
import com.shopping.etrade.response.ResponseAddProductToCard;

public class AddProductToCardMocker {

	public static RequestAddProductToCard generateAddProductToCardRequest() {
		RequestAddProductToCard request = new RequestAddProductToCard();
		request.setCardProductDTO(CardProductMocker.generateCardProductDTO());
		return request;
	}
	
	
	public static ResponseAddProductToCard generateAddProductToCardResponse() {
		ResponseAddProductToCard response = new ResponseAddProductToCard();
		response.setCardProductDTO(CardProductMocker.generateCardProductDTO());
		return response;
	}
}
