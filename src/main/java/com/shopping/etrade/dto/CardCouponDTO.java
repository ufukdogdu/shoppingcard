package com.shopping.etrade.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.shopping.etrade.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardCouponDTO extends BaseDTO {
	@NotNull
	private CardDTO cardDTO;
	@NotNull
	private CouponDTO couponDTO;
	@NotNull
	private Date usageDate;
}
