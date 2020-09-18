package com.shopping.etrade.dto.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDTO {

    private Long id;
    private Integer version;
}
