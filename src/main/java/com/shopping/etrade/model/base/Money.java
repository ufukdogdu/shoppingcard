package com.shopping.etrade.model.base;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.shopping.etrade.dto.base.MoneyDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Money {

	@PositiveOrZero
	private BigDecimal amount;
	@NotNull
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
