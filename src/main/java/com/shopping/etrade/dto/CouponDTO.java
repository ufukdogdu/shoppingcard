package com.shopping.etrade.dto;

import com.shopping.etrade.dto.base.BaseDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.enumtypes.CouponStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponDTO extends BaseDTO {

	private String code;
	private MoneyDTO minCardAmount;
	private MoneyDTO discountAmount;
	private CouponStatus couponStatus;
}
