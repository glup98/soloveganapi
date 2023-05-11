package com.augusto.soloveganbusiness.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PriceDto extends BaseDto {
    @NotNull
    @Positive
    private Long normalPrice;

    @Positive
    private Long offerPrice;
}
