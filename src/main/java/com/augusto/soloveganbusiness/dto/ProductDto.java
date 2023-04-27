package com.augusto.soloveganbusiness.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto extends BaseDto {

    @NotEmpty(message = "Este campo no puede estar en blanco.")
    @Size(min = 3, max = 128, message = "Nombre debe tener entre 3 y 128 caracteres")
    private String name;

    @NotEmpty(message = "Este campo no puede estar en blanco.")
    @Size(min = 5, max = 1000, message = "Descripción no puede tener menos de 5 caracteres")
    private String description;

    private String imgRoute;

    private String portionValue;
}
