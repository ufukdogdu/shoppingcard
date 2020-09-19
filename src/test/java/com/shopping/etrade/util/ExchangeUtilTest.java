package com.shopping.etrade.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.mock.MoneyMocker;

public class ExchangeUtilTest {

	public ExchangeUtil exchangeUtil;
	
	@Before
	public void setup() {
		exchangeUtil = new ExchangeUtil();
	}
	
	@Test
	public void testExchangeMoneySameCurr() {
		MoneyDTO convertingAmount = MoneyMocker.generateMoneyDTO();
		String convertedCurrency = MoneyDTO.TL;
		MoneyDTO resultDTO = ExchangeUtil.exchangeMoney(convertingAmount, convertedCurrency);
		assertThat(resultDTO).isEqualTo(convertingAmount);
	}
	
	@Test
	public void testExchangeMoneyDiffirentCurr() {
		MoneyDTO convertingAmount = MoneyMocker.generateMoneyDTO();
		String convertedCurrency = "USD";
		MoneyDTO resultDTO = ExchangeUtil.exchangeMoney(convertingAmount, convertedCurrency);
		assertThat(resultDTO).isNotEqualTo(convertingAmount);
	}
}


