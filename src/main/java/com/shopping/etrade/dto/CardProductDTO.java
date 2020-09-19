package com.shopping.etrade.dto;

import java.math.BigDecimal;

import com.shopping.etrade.dto.base.BaseDTO;
import com.shopping.etrade.dto.base.MoneyDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardProductDTO extends BaseDTO {

	private ProductDTO productDTO;
	private int quantity;
	private CardDTO cardDTO;

	public static CardProductDTO createMock(CardDTO cardDTO, ProductDTO productDTO, int productCount) {
		CardProductDTO mockDTO = new CardProductDTO();
		mockDTO.setCardDTO(cardDTO);
		mockDTO.setProductDTO(productDTO);
		mockDTO.setQuantity(productCount);
		mockDTO.setVersion(0);
		return mockDTO;
	}
	
	public MoneyDTO getTotalAmount() {
		return productDTO.getPrice().multiply(quantity);
	}
}
