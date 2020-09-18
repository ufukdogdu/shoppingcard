package com.shopping.etrade.response;

import java.io.Serializable;

import com.shopping.etrade.dto.CardProductDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAddProductToCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4340740708139734066L;

	private CardProductDTO cardProductDTO;
}
