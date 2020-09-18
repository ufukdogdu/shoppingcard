package com.shopping.etrade.service.strategy;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;

public interface IDiscountStrategy {

	public MoneyDTO calculateDiscountedAmount(MoneyDTO productAmount, CampaignDiscountDTO campaignDiscountDTO, int quantity) throws IncompatibleCurrencyException;
}
