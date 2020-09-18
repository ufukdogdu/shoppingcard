package com.shopping.etrade.mock;

import java.util.Date;

import com.shopping.etrade.dto.CampaignDTO;
import com.shopping.etrade.model.Campaign;

public class CampaignMocker {

	public static Campaign generateCampaign() {
		Campaign campaign = new Campaign();
		campaign.setCampaignEndDate(new Date(2022, 6, 20));
		campaign.setCampaignStartDate(new Date(2019, 6, 20));
		campaign.setCategoryList(CategoryMocker.generateCategoryList());
		campaign.setDescription("description");
		campaign.setId(1L);
		campaign.setVersion(0);
		return campaign;
	}
	
	public static Campaign generateCampaignOnly() {
		Campaign campaign = new Campaign();
		campaign.setCampaignEndDate(new Date(2022, 6, 20));
		campaign.setCampaignStartDate(new Date(2019, 6, 20));
		campaign.setDescription("description");
		campaign.setId(1L);
		campaign.setVersion(0);
		return campaign;
	}
	
	public static CampaignDTO generateCampaignDTO() {
		CampaignDTO campaignDTO = new CampaignDTO();
		campaignDTO.setCampaignEndDate(new Date(2022, 6, 20));
		campaignDTO.setCampaignStartDate(new Date(2019, 6, 20));
		campaignDTO.setDescription("description");
		campaignDTO.setId(1L);
		campaignDTO.setVersion(0);
		return campaignDTO;
	}
	
	public static CampaignDTO generateCampaignDTOOnly() {
		CampaignDTO campaignDTO = new CampaignDTO();
		campaignDTO.setCampaignEndDate(new Date(2022, 6, 20));
		campaignDTO.setCampaignStartDate(new Date(2019, 6, 20));
		campaignDTO.setDescription("description");
		campaignDTO.setId(1L);
		campaignDTO.setVersion(0);
		return campaignDTO;
	}
}
