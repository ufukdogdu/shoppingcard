package com.shopping.etrade.dto;

import java.util.Date;

import com.shopping.etrade.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardCouponDTO extends BaseDTO {

	private CardDTO cardDTO;
	private CouponDTO couponDTO;
	private Date usageDate;
}
