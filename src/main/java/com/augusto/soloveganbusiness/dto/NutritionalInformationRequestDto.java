package com.augusto.soloveganbusiness.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NutritionalInformationRequestDto {
    @NotEmpty(message = "Este campo no puede estar en blanco.")
    private String description;

    private List<QuantityDto> quantities;
}
