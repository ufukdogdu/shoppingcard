package com.shopping.etrade.service.strategy.campaign;

import org.springframework.stereotype.Service;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.enumtypes.DiscountType;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.service.strategy.IDiscountStrategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class DiscountStrategyContext {

	private IDiscountStrategy discountStrategy;

	public MoneyDTO executeDiscountStrategy(MoneyDTO productAmount, CampaignDiscountDTO campaignDiscountDTO,
			int quantity) throws IncompatibleCurrencyException {
		return discountStrategy.calculateDiscountedAmount(productAmount, campaignDiscountDTO, quantity);
	}

	public IDiscountStrategy calculateDiscountStrategy(DiscountType discountType) {
		IDiscountStrategy strategy;
		if(discountType == null) {
			return new AnyDiscountStrategy();
		}
		switch (discountType) {
		case RATE:
			strategy = new RateDiscountStrategy();
			break;
		default:
			strategy = new AmountDiscountStrategy();
			break;
		}
		return strategy;
	}
}
