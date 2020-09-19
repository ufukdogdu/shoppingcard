package com.shopping.etrade.model;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.shopping.etrade.dto.CampaignDiscountDTO;
import com.shopping.etrade.enumtypes.DiscountType;
import com.shopping.etrade.model.base.IdVersion;
import com.shopping.etrade.model.base.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "campaign_discount")
public class CampaignDiscount extends IdVersion {
	@NotNull
	private int productCount;
	private BigDecimal discountRate;
	@AttributeOverrides({ @AttributeOverride(name = "amount", column = @Column(name = "discount_amount")),
	@AttributeOverride(name = "currency", column = @Column(name = "discount_amount_currency")) })
	private Money discountAmount;
	@ManyToOne(fetch = FetchType.EAGER)
	private Campaign campaign;
	@Enumerated(EnumType.STRING)
	@NotNull
	private DiscountType discountType;

	public static CampaignDiscountDTO toDTO(CampaignDiscount campaignDiscount) {
		CampaignDiscountDTO dto = new CampaignDiscountDTO();
		dto.setId(campaignDiscount.getId());
		dto.setCampaignDTO(Campaign.toDTO(campaignDiscount.getCampaign()));
		dto.setDiscountRate(campaignDiscount.getDiscountRate());
		dto.setProductCount(campaignDiscount.getProductCount());
		dto.setDiscountType(campaignDiscount.getDiscountType());
		dto.setDiscountAmount(Money.toMoneyDTO(campaignDiscount.getDiscountAmount()));
		return dto;
	}
}
