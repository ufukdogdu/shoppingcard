package com.shopping.etrade.service.strategy.campaign;

import java.math.BigDecimal;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.service.strategy.IDiscountStrategy;

public class AnyDiscountStrategy implements IDiscountStrategy {

	@Override
	public MoneyDTO calculateDiscountedAmount(MoneyDTO productAmount, CampaignDiscountDTO campaignDiscountDTO, int quantity) {
		MoneyDTO totalAmount = new MoneyDTO(productAmount.getAmount().multiply(new BigDecimal(quantity)), productAmount.getCurrency());
		return totalAmount;
	}

}
