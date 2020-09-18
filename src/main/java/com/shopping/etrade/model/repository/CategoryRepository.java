package com.shopping.etrade.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.etrade.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
