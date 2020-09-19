package com.shopping.etrade.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.shopping.etrade.dto.CardProductDTO;
import com.shopping.etrade.model.base.IdVersion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "card_product")
public class CardProduct extends IdVersion {
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;
	@NotNull
	private int quantity;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "card_id")
	private Card card;

	public static CardProductDTO toDTO(CardProduct cardProduct) {
		CardProductDTO cardProductDTO = new CardProductDTO();
		cardProductDTO.setCardDTO(Card.toDTO(cardProduct.getCard()));
		cardProductDTO.setProductDTO(Product.toDTO(cardProduct.getProduct()));
		cardProductDTO.setId(cardProduct.getId());
		cardProductDTO.setQuantity(cardProduct.getQuantity());
		return cardProductDTO;
	}
	
	public static CardProduct fromDTO(CardProductDTO dto) {
		CardProduct cardProduct = new CardProduct();
		cardProduct.setQuantity(dto.getQuantity());
		cardProduct.setVersion(0);
		return cardProduct;
	}
}
