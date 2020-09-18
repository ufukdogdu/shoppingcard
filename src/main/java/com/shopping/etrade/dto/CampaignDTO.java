package com.shopping.etrade.dto;

import java.util.Date;

import com.shopping.etrade.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignDTO extends BaseDTO{
	private String description;
	private Date campaignStartDate;
	private Date campaignEndDate;
}
