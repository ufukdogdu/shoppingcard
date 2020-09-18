package com.shopping.etrade.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.etrade.model.Campaign;
import com.shopping.etrade.model.CampaignDiscount;

@Repository
public interface CampaignDiscountRepository extends CrudRepository<CampaignDiscount, Long> {
	public CampaignDiscount findByProductCountAndCampaign(int productCount, Campaign campaign);
}
