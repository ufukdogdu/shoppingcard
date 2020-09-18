package com.shopping.etrade.dto;

import java.math.BigDecimal;

import com.shopping.etrade.dto.base.BaseDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.enumtypes.DiscountType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignDiscountDTO extends BaseDTO {
	private int productCount;
	private BigDecimal discountRate;
	private CampaignDTO campaignDTO;
	private DiscountType discountType;
	private MoneyDTO discountAmount;
}
