package com.shopping.etrade.dto;

import javax.validation.constraints.NotNull;

import com.shopping.etrade.dto.base.BaseDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.enumtypes.CouponStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponDTO extends BaseDTO {
	@NotNull
	private String code;
	@NotNull
	private MoneyDTO minCardAmount;
	@NotNull
	private MoneyDTO discountAmount;
	@NotNull
	private CouponStatus couponStatus;
}
