package com.shopping.etrade.request;

import java.io.Serializable;

import com.shopping.etrade.dto.CardProductDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAddProductToCard implements Serializable {

	private CardProductDTO cardProductDTO;
}
