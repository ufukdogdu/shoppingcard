package com.shopping.etrade.mock;

import java.util.ArrayList;
import java.util.List;

import com.shopping.etrade.dto.CardProductDTO;
import com.shopping.etrade.model.CardProduct;

public class CardProductMocker {

	public static CardProduct generateCardProduct() {
		CardProduct cardProduct = new CardProduct();
		cardProduct.setCard(CardMocker.genarateCardOnly());
		cardProduct.setId(1L);
		cardProduct.setProduct(ProductMocker.generateProductMock());
		cardProduct.setQuantity(2);
		cardProduct.setVersion(0);
		return cardProduct;
	}

	public static CardProductDTO generateCardProductDTO() {
		CardProductDTO cardProductDTO = new CardProductDTO();
		cardProductDTO.setCardDTO(CardMocker.generateCardDTOOnly());
		cardProductDTO.setId(1L);
		cardProductDTO.setProductDTO(ProductMocker.generateProductDTOMock());
		cardProductDTO.setQuantity(2);
		cardProductDTO.setVersion(0);
		return cardProductDTO;
	}

	public static List<CardProduct> generateCardProductList() {
		List<CardProduct> mockList = new ArrayList<>();
		mockList.add(CardProductMocker.generateCardProduct());
		mockList.add(CardProductMocker.generateCardProduct());
		return mockList;
	}

	public static List<CardProductDTO> generateCardProductDTOList() {
		List<CardProductDTO> mockList = new ArrayList<>();
		mockList.add(CardProductMocker.generateCardProductDTO());
		mockList.add(CardProductMocker.generateCardProductDTO());
		return mockList;
	}

}
