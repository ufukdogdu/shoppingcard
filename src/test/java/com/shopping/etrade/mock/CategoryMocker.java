package com.shopping.etrade.mock;

import java.util.ArrayList;
import java.util.List;

import com.shopping.etrade.dto.CategoryDTO;
import com.shopping.etrade.model.Category;

public class CategoryMocker {

	public static Category generateCategory() {
		Category category = new Category();
		category.setCampaign(CampaignMocker.generateCampaignOnly());
		category.setId(2L);
		category.setParentCategoryId(1L);
		category.setTitle("category");
		category.setVersion(0);
		return category;
	}
	
	public static CategoryDTO generateCategoryDTO() {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCampaignDTO(CampaignMocker.generateCampaignDTOOnly());
		categoryDTO.setId(2L);
		categoryDTO.setParentCategoryId(1L);
		categoryDTO.setTitle("category");
		categoryDTO.setVersion(0);
		return categoryDTO;
	}
	
	public static List<Category> generateCategoryList(){
		List<Category> mockList = new ArrayList<>();
		for(int i= 0; i<3; ++i) {
			mockList.add(CategoryMocker.generateCategory());
		}
		return mockList;
	}
}
