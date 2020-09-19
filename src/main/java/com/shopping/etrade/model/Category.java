package com.shopping.etrade.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.shopping.etrade.dto.CategoryDTO;
import com.shopping.etrade.model.base.IdVersion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends IdVersion {
	@NotNull
	private String title;
	private Long parentCategoryId;
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
    private Campaign campaign;
	
	public static CategoryDTO toDTO(Category category) {
		CategoryDTO dto = new CategoryDTO();
		dto.setCampaignDTO(Campaign.toDTO(category.getCampaign()));
		dto.setId(category.getId());
		dto.setParentCategoryId(category.getParentCategoryId());
		dto.setTitle(category.getTitle());
		return dto;
	}
	
}
