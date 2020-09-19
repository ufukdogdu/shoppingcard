package com.shopping.etrade.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.shopping.etrade.dto.ProductDTO;
import com.shopping.etrade.enumtypes.QuantityType;
import com.shopping.etrade.model.base.IdVersion;
import com.shopping.etrade.model.base.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends IdVersion {
	@NotNull
	private String title;
	@NotNull
	@AttributeOverrides({ @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
		@AttributeOverride(name = "currency", column = @Column(name = "price_amount_currency")) })
	private Money price;
	@Enumerated(EnumType.STRING)
	@NotNull
	private QuantityType quantityType;
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	@NotNull
	private String firmName;
	
	public static ProductDTO toDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setCategoryDTO(Category.toDTO(product.getCategory()));
		productDTO.setId(product.getId());
		productDTO.setPrice(Money.toMoneyDTO(product.getPrice()));
		productDTO.setQuantityType(product.getQuantityType());
		productDTO.setTitle(product.getTitle());
		productDTO.setFirmName(product.getFirmName());
		return productDTO;
	}

}
