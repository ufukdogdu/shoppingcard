package com.shopping.etrade.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.etrade.model.Campaign;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, Long> {
}
