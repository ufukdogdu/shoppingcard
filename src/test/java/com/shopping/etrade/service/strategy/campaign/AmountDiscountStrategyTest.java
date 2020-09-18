package com.shopping.etrade.service.strategy.campaign;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.mock.CampaignDiscountMocker;
import com.shopping.etrade.mock.MoneyMocker;

public class AmountDiscountStrategyTest {


	public AmountDiscountStrategy amountDiscountStrategy;

	@Before
	public void setup() {
		amountDiscountStrategy = new AmountDiscountStrategy();
	}
	@Test
	public void testCalculateDiscountedAmount() throws IncompatibleCurrencyException {
		MoneyDTO productAmount = MoneyMocker.generateMoneyDTO();
		CampaignDiscountDTO campaignDiscountDTO = CampaignDiscountMocker.generateCampaignDiscountDTO();
		MoneyDTO moneyDTO = amountDiscountStrategy.calculateDiscountedAmount(productAmount, campaignDiscountDTO, 1);
		assertThat(productAmount.getCurrency()).isEqualTo(moneyDTO.getCurrency());
		assertThat(productAmount.substractMoney(campaignDiscountDTO.getDiscountAmount()).getAmount())
				.isEqualTo(moneyDTO.getAmount());
	}
}
