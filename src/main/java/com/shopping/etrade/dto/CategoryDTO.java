package com.shopping.etrade.dto;

import com.shopping.etrade.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO extends BaseDTO{
	private String title;
	private Long parentCategoryId;
    private CampaignDTO campaignDTO;
}
