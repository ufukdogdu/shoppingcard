package com.shopping.etrade.mock;

import java.util.Date;

import com.shopping.etrade.dto.CardCouponDTO;
import com.shopping.etrade.model.CardCoupon;

public class CardCouponMocker {

	public static CardCoupon generateCardCoupon() {
		CardCoupon cardCoupon = new CardCoupon();
		cardCoupon.setCard(CardMocker.genarateCard());
		cardCoupon.setCoupon(CouponMocker.generateCoupon());
		cardCoupon.setId(1L);
		cardCoupon.setUsageDate(new Date());
		cardCoupon.setVersion(0);
		return cardCoupon;
	}

	public static CardCouponDTO generateCardCouponDTO() {
		CardCouponDTO cardCouponDTO = new CardCouponDTO();
		cardCouponDTO.setCardDTO(CardMocker.generateCardDTO());
		cardCouponDTO.setCouponDTO(CouponMocker.generateCouponDTO());
		cardCouponDTO.setId(1L);
		cardCouponDTO.setUsageDate(new Date());
		cardCouponDTO.setVersion(0);
		return cardCouponDTO;
	}
}
