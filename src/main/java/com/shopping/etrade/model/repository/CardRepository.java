package com.shopping.etrade.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.etrade.model.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
}
