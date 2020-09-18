package com.shopping.etrade.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.etrade.enumtypes.CouponStatus;
import com.shopping.etrade.model.Coupon;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {

	public Coupon findByCodeAndCouponStatus(String code, CouponStatus status);
}
