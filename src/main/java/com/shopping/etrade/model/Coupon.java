package com.shopping.etrade.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.shopping.etrade.dto.CouponDTO;
import com.shopping.etrade.enumtypes.CouponStatus;
import com.shopping.etrade.model.base.IdVersion;
import com.shopping.etrade.model.base.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coupon")
public class Coupon extends IdVersion {

	private String code;
	@AttributeOverrides({ @AttributeOverride(name = "amount", column = @Column(name = "min_card_amount")),
			@AttributeOverride(name = "currency", column = @Column(name = "min_card_amount_currency")) })
	private Money minCardAmount;
	@AttributeOverrides({ @AttributeOverride(name = "amount", column = @Column(name = "discount_amount")),
			@AttributeOverride(name = "currency", column = @Column(name = "discount_amount_currency")) })
	private Money discountAmount;
	@Enumerated(EnumType.STRING)
	private CouponStatus couponStatus;

	public static CouponDTO toDTO(Coupon coupon) {
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setCode(coupon.getCode());
		couponDTO.setDiscountAmount(Money.toMoneyDTO(coupon.getDiscountAmount()));
		couponDTO.setId(coupon.getId());
		couponDTO.setMinCardAmount(Money.toMoneyDTO(coupon.getMinCardAmount()));
		couponDTO.setCouponStatus(coupon.getCouponStatus());
		return couponDTO;
	}
}
