package com.augusto.soloveganbusiness.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QuantityDto extends BaseDto {
    @NotNull(message = "Este campo no puede ser nulo.")
    private String volume;

    @NotNull(message = "Este campo no puede ser nulo.")
    private String portion;
}
