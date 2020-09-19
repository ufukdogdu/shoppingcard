package com.shopping.etrade.mock;

import com.shopping.etrade.dto.ProductDTO;
import com.shopping.etrade.enumtypes.QuantityType;
import com.shopping.etrade.model.Product;

public class ProductMocker {

	public static Product generateProductMock() {
		Product product = new Product();
		product.setCategory(CategoryMocker.generateCategory());
		product.setFirmName("Firm Name");
		product.setId(1L);
		product.setPrice(MoneyMocker.generateMoney());
		product.setQuantityType(QuantityType.COUNT);
		product.setTitle("Product");
		product.setVersion(0);
		return product;
	}
	
	public static ProductDTO generateProductDTOMock() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setCategoryDTO(CategoryMocker.generateCategoryDTO());
		productDTO.setFirmName("Firm Name");
		productDTO.setId(1L);
		productDTO.setPrice(MoneyMocker.generateMoneyDTO());
		productDTO.setQuantityType(QuantityType.COUNT);
		productDTO.setTitle("Product");
		productDTO.setVersion(0);
		return productDTO;
	}
}
