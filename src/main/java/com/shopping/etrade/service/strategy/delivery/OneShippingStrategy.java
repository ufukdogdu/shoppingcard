package com.shopping.etrade.service.strategy.delivery;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.service.strategy.IDeliveryStrategy;

public class OneShippingStrategy implements IDeliveryStrategy {

	@Override
	public MoneyDTO calculateDeliveryCost(int numberOfProducts) throws IncompatibleCurrencyException {
		if (numberOfProducts > 4) {
			return IDeliveryStrategy.BASE_SHIPPING_AMOUNT.addMoney(IDeliveryStrategy.MORE_THAN_FIVE_PIECE_AMOUNT);
		}
		return IDeliveryStrategy.BASE_SHIPPING_AMOUNT.addMoney(IDeliveryStrategy.PER_PIECE_AMOUNT.multiply(numberOfProducts));
	}

}
