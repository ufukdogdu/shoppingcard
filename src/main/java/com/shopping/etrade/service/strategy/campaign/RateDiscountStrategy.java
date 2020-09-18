package com.shopping.etrade.service.strategy.campaign;

import java.math.BigDecimal;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.service.strategy.IDiscountStrategy;

public class RateDiscountStrategy implements IDiscountStrategy {

	@Override
	public MoneyDTO calculateDiscountedAmount(MoneyDTO productAmount, CampaignDiscountDTO campaignDiscountDTO,
			int quantity) {
		MoneyDTO perProductAmount = productAmount.calcuatePercentage(productAmount,
				campaignDiscountDTO.getDiscountRate());
		MoneyDTO totalAmount = new MoneyDTO(perProductAmount.getAmount().multiply(new BigDecimal(quantity)),
				perProductAmount.getCurrency());
		return totalAmount;
	}

}
