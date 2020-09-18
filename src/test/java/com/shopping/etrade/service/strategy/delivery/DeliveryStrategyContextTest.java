package com.shopping.etrade.service.strategy.delivery;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.mock.MoneyMocker;
import com.shopping.etrade.service.strategy.IDeliveryStrategy;

public class DeliveryStrategyContextTest {

	DeliveryStrategyContext deliveryStrategyContext;
	IDeliveryStrategy deliveryStrategy;
	
	@Before
	public void setup(){
		deliveryStrategy = Mockito.mock(IDeliveryStrategy.class);
		deliveryStrategyContext = new DeliveryStrategyContext();
	}
	
	@Test
	public void testCalculateDeliveryStrategy() {
		IDeliveryStrategy deliveryStrategy = deliveryStrategyContext.calculateDeliveryStrategy(1);
		assertThat(deliveryStrategy).isOfAnyClassIn(OneShippingStrategy.class);
		deliveryStrategy = deliveryStrategyContext.calculateDeliveryStrategy(2);
		assertThat(deliveryStrategy).isOfAnyClassIn(TwoShippingStrategy.class);
		deliveryStrategy = deliveryStrategyContext.calculateDeliveryStrategy(3);
		assertThat(deliveryStrategy).isOfAnyClassIn(MoreThanTwoShippingStrategy.class);
	}
	
	@Test
	public void testExecuteDeliveryStrategy() throws IncompatibleCurrencyException {
		MoneyDTO mockMoneyDTO = MoneyMocker.generateMoneyDTO();
		deliveryStrategyContext.setDeliveryStrategy(new OneShippingStrategy());
		Mockito.when(deliveryStrategy.calculateDeliveryCost(1)).thenReturn(mockMoneyDTO);
		MoneyDTO resultDTO = deliveryStrategyContext.executeDeliveryStrategy(1);
		assertThat(resultDTO).isNotNull();
	}
		

}
