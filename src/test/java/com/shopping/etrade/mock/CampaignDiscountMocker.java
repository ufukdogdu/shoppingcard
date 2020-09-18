package com.shopping.etrade.mock;

import java.math.BigDecimal;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.enumtypes.DiscountType;
import com.shopping.etrade.model.CampaignDiscount;

public class CampaignDiscountMocker {

	public static CampaignDiscount generateCampaignDiscount() {
		CampaignDiscount campaignDiscount = new CampaignDiscount();
		campaignDiscount.setCampaign(CampaignMocker.generateCampaign());
		campaignDiscount.setDiscountAmount(MoneyMocker.generateMoney());
		campaignDiscount.setDiscountRate(new BigDecimal(10));
		campaignDiscount.setDiscountType(DiscountType.RATE);
		campaignDiscount.setId(1L);
		campaignDiscount.setProductCount(3);
		campaignDiscount.setVersion(0);
		return campaignDiscount;
	}
	
	public static CampaignDiscountDTO generateCampaignDiscountDTO() {
		CampaignDiscountDTO campaignDiscountDTO = new CampaignDiscountDTO();
		campaignDiscountDTO.setCampaignDTO(CampaignMocker.generateCampaignDTO());
		campaignDiscountDTO.setDiscountAmount(MoneyMocker.generateMoneyDTO());
		campaignDiscountDTO.setDiscountRate(new BigDecimal(10));
		campaignDiscountDTO.setDiscountType(DiscountType.RATE);
		campaignDiscountDTO.setId(1L);
		campaignDiscountDTO.setProductCount(3);
		campaignDiscountDTO.setVersion(0);
		return campaignDiscountDTO;
	}
}
