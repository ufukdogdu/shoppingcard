package com.shopping.etrade.util;

import java.math.BigDecimal;
import java.util.Random;

import com.shopping.etrade.dto.base.MoneyDTO;

public class ExchangeUtil {

	public static MoneyDTO exchangeMoney(MoneyDTO convertingAmount, String convertedCurrency) {
		if (convertingAmount.getCurrency().equals(convertedCurrency)) {
			return convertingAmount;
		} else {
			// burda dis servisten exchange degerleri cekildigi farzedilmistir.
			Random curr = new Random();
			int randomCurr = curr.nextInt(10);
			return new MoneyDTO(convertingAmount.getAmount().multiply(new BigDecimal(randomCurr)), convertedCurrency);
		}
	}
}
