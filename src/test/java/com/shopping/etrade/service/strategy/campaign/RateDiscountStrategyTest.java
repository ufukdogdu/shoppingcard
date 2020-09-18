package com.shopping.etrade.service.strategy.campaign;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.mock.CampaignDiscountMocker;
import com.shopping.etrade.mock.MoneyMocker;

public class RateDiscountStrategyTest {


	public RateDiscountStrategy rateDiscountStrategy;

	@Before
	public void setup() {
		rateDiscountStrategy = new RateDiscountStrategy();
	}
	@Test
	public void testCalculateDiscountedAmount() throws IncompatibleCurrencyException {
		MoneyDTO productAmount = MoneyMocker.generateMoneyDTO();
		CampaignDiscountDTO campaignDiscountDTO = CampaignDiscountMocker.generateCampaignDiscountDTO();
		MoneyDTO moneyDTO = rateDiscountStrategy.calculateDiscountedAmount(productAmount, campaignDiscountDTO, 1);
		assertThat(productAmount.getCurrency()).isEqualTo(moneyDTO.getCurrency());
		assertThat(productAmount.getAmount().subtract(productAmount.getAmount().multiply(campaignDiscountDTO.getDiscountRate()).divide(new BigDecimal(100), RoundingMode.HALF_UP)))
				.isEqualTo(moneyDTO.getAmount());
	}
}
