package com.shopping.etrade.service.strategy.delivery;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.service.strategy.IDeliveryStrategy;

public class MoreThanTwoShippingStrategy  implements IDeliveryStrategy {

	@Override
	public MoneyDTO calculateDeliveryCost(int numberOfProducts) {
		return IDeliveryStrategy.BASE_SHIPPING_AMOUNT;
	}

}
