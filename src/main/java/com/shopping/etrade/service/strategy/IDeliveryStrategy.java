package com.shopping.etrade.service.strategy;

import java.math.BigDecimal;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;

public interface IDeliveryStrategy {
	
	public static MoneyDTO BASE_SHIPPING_AMOUNT = new MoneyDTO(BigDecimal.TEN, MoneyDTO.TL);
	public static MoneyDTO PER_PIECE_AMOUNT = new MoneyDTO(BigDecimal.ONE, MoneyDTO.TL);
	public static MoneyDTO MORE_THAN_FIVE_PIECE_AMOUNT = new MoneyDTO(new BigDecimal("5"), MoneyDTO.TL);
	
	public MoneyDTO calculateDeliveryCost(int numberOfProducts) throws IncompatibleCurrencyException;
}
