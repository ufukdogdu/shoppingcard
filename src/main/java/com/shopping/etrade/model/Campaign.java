package com.shopping.etrade.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.shopping.etrade.dto.CampaignDTO;
import com.shopping.etrade.model.base.IdVersion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "campaign")
public class Campaign extends IdVersion {
	@NotNull
	private String description;
	@NotNull
	private Date campaignStartDate;
	@NotNull
	private Date campaignEndDate;
	@OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER)
	private List<Category> categoryList;
	
	public static CampaignDTO toDTO(Campaign campaign) {
		CampaignDTO dto = new CampaignDTO();
		dto.setId(campaign.getId());
		dto.setCampaignEndDate(campaign.getCampaignEndDate());
		dto.setCampaignStartDate(campaign.getCampaignStartDate());
		dto.setDescription(campaign.getDescription());
		return dto;
	}
}
