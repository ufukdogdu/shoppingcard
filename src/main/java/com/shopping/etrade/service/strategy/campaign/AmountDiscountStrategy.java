package com.shopping.etrade.service.strategy.campaign;

import java.math.BigDecimal;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.service.strategy.IDiscountStrategy;

public class AmountDiscountStrategy implements IDiscountStrategy {

	@Override
	public MoneyDTO calculateDiscountedAmount(MoneyDTO productAmount, CampaignDiscountDTO campaignDiscountDTO,
			int quantity) throws IncompatibleCurrencyException {
		MoneyDTO perProductAmount = productAmount.substractMoney(campaignDiscountDTO.getDiscountAmount());
		MoneyDTO totalAmount = new MoneyDTO(perProductAmount.getAmount().multiply(new BigDecimal(quantity)),
				perProductAmount.getCurrency());
		return totalAmount;
	}

}
