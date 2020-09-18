package com.shopping.etrade.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.etrade.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
