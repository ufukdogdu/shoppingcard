package com.shopping.etrade.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.etrade.model.Card;
import com.shopping.etrade.model.CardProduct;
import com.shopping.etrade.model.Product;

@Repository
public interface CardProductRepository extends CrudRepository<CardProduct, Long> {
	public CardProduct findByCardAndProduct(Card card, Product product);

	public List<CardProduct> findAllByCard(Card card);
}
