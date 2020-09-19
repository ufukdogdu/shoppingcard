package com.shopping.etrade.dto.base;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.util.ExchangeUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyDTO implements Comparable<MoneyDTO> {
	public static final String TL = "TL";
	private static final String LEFT = "L";
	private static final String RIGHT = "R";
	private static final String EMPTY = " ";
	@PositiveOrZero
	private BigDecimal amount;
	@NotNull
	private String currency;
	Logger logger = LoggerFactory.getLogger(MoneyDTO.class);
	public MoneyDTO(BigDecimal amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public MoneyDTO substractMoney(MoneyDTO subsAmount) throws IncompatibleCurrencyException {
		MoneyDTO calculatedAmount = new MoneyDTO(this.amount, this.currency);
		if (this.getCurrency().equals(subsAmount.getCurrency())) {
			calculatedAmount.setAmount(this.amount.subtract(subsAmount.getAmount()));
		} else {
			throw new IncompatibleCurrencyException();
		}
		return calculatedAmount;
	}

	public MoneyDTO calcuatePercentage(MoneyDTO totalAmount, BigDecimal percentage) {
		MoneyDTO calculatedAmount = totalAmount;
		calculatedAmount.setAmount(
				calculatedAmount.getAmount().multiply(percentage).divide(new BigDecimal(100), RoundingMode.HALF_UP));
		return calculatedAmount;
	}

	public MoneyDTO multiply(int multiplier) {
		BigDecimal amount = this.amount.multiply(new BigDecimal(multiplier));
		MoneyDTO moneyDTO = new MoneyDTO(amount, this.currency);
		return moneyDTO;
	}

	public MoneyDTO addMoney(MoneyDTO addedAmount) throws IncompatibleCurrencyException {

		if (addedAmount.getCurrency().equals(this.getCurrency())) {
			addedAmount.setAmount(addedAmount.getAmount().add(this.amount));
		} else {
			throw new IncompatibleCurrencyException();
		}
		return addedAmount;
	}

	public void printAmount(String description) {
		String amount = this.amount.setScale(2) + EMPTY + this.currency;
		logger.info(this.addEmptyLine(description, 35, RIGHT) + this.addEmptyLine(amount, 10, LEFT));
	}

	private String addEmptyLine(String word, int maxLine, String direction) {
		String newWord = word;
		if (word.length() > maxLine) {
			return word.substring(0, word.length() - 1);
		} else {
			for (int i = 0; i < maxLine - word.length(); ++i) {
				if (RIGHT.endsWith(direction)) {
					newWord = newWord.concat(EMPTY);
				} else {
					newWord = EMPTY.concat(newWord);
				}
			}
			return newWord;
		}
	}

	@Override
	public int compareTo(MoneyDTO arg0) {
		MoneyDTO exchangedMoney = arg0;
		if (!this.currency.equals(exchangedMoney.getCurrency())) {
			exchangedMoney = ExchangeUtil.exchangeMoney(exchangedMoney, this.getCurrency());
		}
		return this.amount.compareTo(exchangedMoney.getAmount());
	}
}
