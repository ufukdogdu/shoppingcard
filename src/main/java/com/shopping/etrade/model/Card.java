package com.shopping.etrade.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.shopping.etrade.dto.CardDTO;
import com.shopping.etrade.model.base.IdVersion;
import com.shopping.etrade.model.base.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "card")
public class Card extends IdVersion {
	@AttributeOverrides({ @AttributeOverride(name = "amount", column = @Column(name = "shipping_amount")),
			@AttributeOverride(name = "currency", column = @Column(name = "shipping_amount_currency")) })
	private Money shippingAmount;
	@AttributeOverrides({ @AttributeOverride(name = "amount", column = @Column(name = "campapign_discound_amount")),
			@AttributeOverride(name = "currency", column = @Column(name = "campaign_discound_amount_currency")) })
	private Money campaignDiscount;
	@AttributeOverrides({ @AttributeOverride(name = "amount", column = @Column(name = "coupon_discound_amount")),
			@AttributeOverride(name = "currency", column = @Column(name = "coupon_discound_amount_currency")) })
	private Money couponDiscount;
	@AttributeOverrides({ @AttributeOverride(name = "amount", column = @Column(name = "basket_amount")),
			@AttributeOverride(name = "currency", column = @Column(name = "basket_amount_currency")) })
	private Money basketAmount;
	@OneToMany(mappedBy="card", fetch = FetchType.EAGER)
	private List<CardProduct> cardProductList;

	public static CardDTO toDTO(Card card) {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setCampaignDiscount(Money.toMoneyDTO(card.getCampaignDiscount()));
		cardDTO.setCouponDiscount(Money.toMoneyDTO(card.getCouponDiscount()));
		cardDTO.setId(card.getId());
		cardDTO.setBasketAmount((Money.toMoneyDTO(card.getBasketAmount())));
		cardDTO.setShippingAmount(Money.toMoneyDTO(card.getShippingAmount()));
		return cardDTO;
	}
}
