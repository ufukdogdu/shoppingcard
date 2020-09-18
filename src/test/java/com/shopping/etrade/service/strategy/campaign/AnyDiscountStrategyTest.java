package com.shopping.etrade.service.strategy.campaign;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.mock.CampaignDiscountMocker;
import com.shopping.etrade.mock.MoneyMocker;

public class AnyDiscountStrategyTest {


	public AnyDiscountStrategy anyDiscountStrategy;

	@Before
	public void setup() {
		anyDiscountStrategy = new AnyDiscountStrategy();
	}
	@Test
	public void testCalculateDiscountedAmount() throws IncompatibleCurrencyException {
		MoneyDTO productAmount = MoneyMocker.generateMoneyDTO();
		CampaignDiscountDTO campaignDiscountDTO = CampaignDiscountMocker.generateCampaignDiscountDTO();
		MoneyDTO moneyDTO = anyDiscountStrategy.calculateDiscountedAmount(productAmount, campaignDiscountDTO, 1);
		assertThat(moneyDTO.getAmount()).isEqualTo(productAmount.getAmount());
		assertThat(moneyDTO.getCurrency()).isEqualTo(productAmount.getCurrency());
	}
}
