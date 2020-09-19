package com.shopping.etrade.dto;

import javax.validation.constraints.NotNull;

import com.shopping.etrade.dto.base.BaseDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.enumtypes.QuantityType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends BaseDTO {
	@NotNull
	private String title;
	@NotNull
	private MoneyDTO price;
	@NotNull
	private QuantityType quantityType;
	@NotNull
	private CategoryDTO categoryDTO;
	@NotNull
	private String firmName;

	public static ProductDTO createMock(Long id) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(id);
		return productDTO;
	}
}
