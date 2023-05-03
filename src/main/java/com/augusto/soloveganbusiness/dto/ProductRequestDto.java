package com.augusto.soloveganbusiness.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductRequestDto {
    private ProductDto productDto;

    private PriceDto priceDto;

}
