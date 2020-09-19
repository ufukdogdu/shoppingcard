package com.shopping.etrade.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private String code;
	@AttributeOverrides({ @AttributeOverride(name = "amount", column = @Column(name = "min_card_amount")),
			@AttributeOverride(name = "currency", column = @Column(name = "min_card_amount_currency")) })
	@NotNull
	private Money minCardAmount;
	@AttributeOverrides({ @AttributeOverride(name = "amount", column = @Column(name = "discount_amount")),
			@AttributeOverride(name = "currency", column = @Column(name = "discount_amount_currency")) })
	@NotNull
	private Money discountAmount;
	@NotNull
	@Enumerated(EnumType.STRING)
	private CouponStatus couponStatus;

	public static CouponDTO  toDTO(Coupon coupon) {
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setCode(coupon.getCode());
		couponDTO.setDiscountAmount(Money.toMoneyDTO(coupon.getDiscountAmount()));
		couponDTO.setCouponStatus(coupon.getCouponStatus());
		couponDTO.setId(coupon.getId());
		couponDTO.setMinCardAmount(Money.toMoneyDTO(coupon.getMinCardAmount()));
		return couponDTO;
	}
}
