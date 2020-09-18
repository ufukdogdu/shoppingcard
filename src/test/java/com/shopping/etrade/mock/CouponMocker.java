package com.shopping.etrade.mock;

import com.shopping.etrade.dto.CouponDTO;
import com.shopping.etrade.enumtypes.CouponStatus;
import com.shopping.etrade.model.Coupon;

public class CouponMocker {

	public static Coupon generateCoupon() {
		Coupon coupon = new Coupon();
		coupon.setCode("code");
		coupon.setCouponStatus(CouponStatus.ACTIVE);
		coupon.setDiscountAmount(MoneyMocker.generateMoney());
		coupon.setId(1L);
		coupon.setMinCardAmount(MoneyMocker.generateMoney());
		coupon.setVersion(0);
		return coupon;
	}
	
	public static CouponDTO generateCouponDTO() {
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setCode("code");
		couponDTO.setCouponStatus(CouponStatus.ACTIVE);
		couponDTO.setDiscountAmount(MoneyMocker.generateMoneyDTO());
		couponDTO.setId(1L);
		couponDTO.setMinCardAmount(MoneyMocker.generateMoneyDTO());
		couponDTO.setVersion(0);
		return couponDTO;
	}
}
