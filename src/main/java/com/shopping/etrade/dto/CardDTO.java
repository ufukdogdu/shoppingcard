package com.shopping.etrade.dto;

import java.util.List;

import com.shopping.etrade.dto.base.BaseDTO;
import com.shopping.etrade.dto.base.MoneyDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO extends BaseDTO {

	private MoneyDTO shippingAmount;
	private MoneyDTO campaignDiscount;
	private MoneyDTO couponDiscount;
	private MoneyDTO basketAmount;
	private List<CardProductDTO> cardProductDTOList;

	public static CardDTO createMock(Long id) {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId(id);
		return cardDTO;
	}
}
