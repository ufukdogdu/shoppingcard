package com.shopping.etrade.model.base;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import com.shopping.etrade.dto.base.MoneyDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Money {

	private BigDecimal amount;
	private String currency;

	public Money() {

	}

	public Money(BigDecimal amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public static MoneyDTO toMoneyDTO(Money money) {
		if (money == null) {
			return null;
		}
		return new MoneyDTO(money.getAmount(), money.getCurrency());
	}
	
	public static Money fromMoneyDTO(MoneyDTO moneyDTO) {
		if (moneyDTO == null) {
			return null;
		}
		return new Money(moneyDTO.getAmount(), moneyDTO.getCurrency());
	}
}
