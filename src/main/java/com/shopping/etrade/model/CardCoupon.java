package com.shopping.etrade.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.shopping.etrade.dto.CardCouponDTO;
import com.shopping.etrade.model.base.IdVersion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "card_coupon")
public class CardCoupon extends IdVersion {
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "card_id")
	private Card card;
	@NotNull
	private Date usageDate;
	
	public static CardCoupon createCardCoupon(Card card, Coupon coupon) {
		CardCoupon cardCoupon = new CardCoupon();
		cardCoupon.setCard(card);
		cardCoupon.setCoupon(coupon);
		cardCoupon.setUsageDate(new Date());
		cardCoupon.setVersion(0);
		return cardCoupon;
	}
	
	public static CardCouponDTO toDTO(CardCoupon cardCoupon) {
		CardCouponDTO dto = new CardCouponDTO();
		dto.setCardDTO(Card.toDTO(cardCoupon.getCard()));
		dto.setCouponDTO(Coupon.toDTO(cardCoupon.getCoupon()));
		dto.setId(cardCoupon.getId());
		dto.setUsageDate(cardCoupon.getUsageDate());
		return dto;
	}
}
