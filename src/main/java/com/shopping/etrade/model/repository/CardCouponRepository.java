package com.shopping.etrade.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.etrade.model.CardCoupon;

@Repository
public interface CardCouponRepository extends CrudRepository<CardCoupon, Long> {

}
