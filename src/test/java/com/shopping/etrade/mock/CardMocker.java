package com.shopping.etrade.mock;

import com.shopping.etrade.dto.CardDTO;
import com.shopping.etrade.model.Card;

public class CardMocker {

	public static Card genarateCard() {
		Card card = new Card();
		card.setBasketAmount(MoneyMocker.generateMoney());
		card.setCampaignDiscount(MoneyMocker.generateMoney());
		card.setCardProductList(CardProductMocker.generateCardProductList());
		card.setCouponDiscount(MoneyMocker.generateMoney());
		card.setId(1L);
		card.setShippingAmount(MoneyMocker.generateMoney());
		card.setVersion(0);
		return card;
	}
	
	public static Card genarateCardOnly() {
		Card card = new Card();
		card.setBasketAmount(MoneyMocker.generateMoney());
		card.setCampaignDiscount(MoneyMocker.generateMoney());
		card.setCouponDiscount(MoneyMocker.generateMoney());
		card.setId(1L);
		card.setShippingAmount(MoneyMocker.generateMoney());
		card.setVersion(0);
		return card;
	}

	public static CardDTO generateCardDTO() {
		CardDTO dto = new CardDTO();
		dto.setBasketAmount(MoneyMocker.generateMoneyDTO());
		dto.setCampaignDiscount(MoneyMocker.generateMoneyDTO());
		dto.setCardProductDTOList(CardProductMocker.generateCardProductDTOList());
		dto.setCouponDiscount(MoneyMocker.generateMoneyDTO());
		dto.setId(1L);
		dto.setShippingAmount(MoneyMocker.generateMoneyDTO());
		dto.setVersion(0);
		return dto;
	}
	
	public static CardDTO generateCardDTOOnly() {
		CardDTO dto = new CardDTO();
		dto.setBasketAmount(MoneyMocker.generateMoneyDTO());
		dto.setCampaignDiscount(MoneyMocker.generateMoneyDTO());
		dto.setCouponDiscount(MoneyMocker.generateMoneyDTO());
		dto.setId(1L);
		dto.setShippingAmount(MoneyMocker.generateMoneyDTO());
		dto.setVersion(0);
		return dto;
	}
}
