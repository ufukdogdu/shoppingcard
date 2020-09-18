package com.shopping.etrade.service.strategy.delivery;

import org.springframework.stereotype.Service;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.service.strategy.IDeliveryStrategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class DeliveryStrategyContext {

	private IDeliveryStrategy deliveryStrategy;

	public MoneyDTO executeDeliveryStrategy(int numberOfProducts) throws IncompatibleCurrencyException {
		return deliveryStrategy.calculateDeliveryCost(numberOfProducts);
	}

	public IDeliveryStrategy calculateDeliveryStrategy(int shippingCount) {
		IDeliveryStrategy strategy;
		switch (shippingCount) {
		case 1:
			strategy = new OneShippingStrategy();
			break;
		case 2:
			strategy = new TwoShippingStrategy();
			break;
		default:
			strategy = new MoreThanTwoShippingStrategy();
		}
		return strategy;
	}
}
