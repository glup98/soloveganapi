package com.augusto.soloveganbusiness.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PriceDto extends BaseDto {
    @NotNull
    @Positive
    private int normalPrice;

    @Positive
    private int offerPrice;

    @NotEmpty(message = "Este campo no puede estar vacío.")
    @URL(message = "Por favor ingrese una URL válida")
    private String imgRouteLinks;
}
