package com.shopping.etrade.dto;

import com.shopping.etrade.dto.base.BaseDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.enumtypes.QuantityType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends BaseDTO {
	private String title;
	private MoneyDTO price;
	private QuantityType quantity;
	private CategoryDTO categoryDTO;
	private String firmName;

	public static ProductDTO createMock(Long id) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(id);
		return productDTO;
	}
}
