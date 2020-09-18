package com.shopping.etrade.service.strategy.campaign;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.enumtypes.DiscountType;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.mock.CampaignDiscountMocker;
import com.shopping.etrade.mock.MoneyMocker;
import com.shopping.etrade.service.strategy.IDiscountStrategy;

public class DiscountStrategyContextTest {

	DiscountStrategyContext discountStrategyContext;
	IDiscountStrategy discountStrategy;
	
	@Before
	public void setup(){
		discountStrategy = Mockito.mock(IDiscountStrategy.class);
		discountStrategyContext = new DiscountStrategyContext();
	}
	
	@Test
	public void testCalculateDiscountStrategy() {
		IDiscountStrategy discountStrategy = discountStrategyContext.calculateDiscountStrategy(DiscountType.AMOUNT);
		assertThat(discountStrategy).isOfAnyClassIn(AmountDiscountStrategy.class);
		discountStrategy = discountStrategyContext.calculateDiscountStrategy(DiscountType.RATE);
		assertThat(discountStrategy).isOfAnyClassIn(RateDiscountStrategy.class);
		discountStrategy = discountStrategyContext.calculateDiscountStrategy(null);
		assertThat(discountStrategy).isOfAnyClassIn(AnyDiscountStrategy.class);
	}
	
	@Test
	public void testExecuteDiscountStrategy() throws IncompatibleCurrencyException {
		MoneyDTO mockMoneyDTO = MoneyMocker.generateMoneyDTO();
		CampaignDiscountDTO campaignDiscountDTO = CampaignDiscountMocker.generateCampaignDiscountDTO();
		Mockito.when(discountStrategy.calculateDiscountedAmount(mockMoneyDTO, campaignDiscountDTO, 2)).thenReturn(mockMoneyDTO);
		discountStrategyContext.setDiscountStrategy(new AmountDiscountStrategy());
		MoneyDTO resultDTO = discountStrategyContext.executeDiscountStrategy(mockMoneyDTO, campaignDiscountDTO, 2);
		assertThat(resultDTO).isNotNull();
	}
}
