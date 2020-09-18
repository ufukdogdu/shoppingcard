package com.shopping.etrade.mock;

import java.math.BigDecimal;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.model.base.Money;

public class MoneyMocker {

	public static Money generateMoney() {
		Money money = new Money();
		money.setAmount(BigDecimal.TEN);
		money.setCurrency("TL");
		return money;
	}

	public static MoneyDTO generateMoneyDTO() {
		MoneyDTO moneyDTO = new MoneyDTO(BigDecimal.TEN, "TL");
		return moneyDTO;
	}
}
