package com.shopping.etrade.dto.base;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDTO {
	@NotNull
    private Long id;
    private Integer version;
}
