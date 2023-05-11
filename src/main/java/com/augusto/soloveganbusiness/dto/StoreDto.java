package com.augusto.soloveganbusiness.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StoreDto extends BaseDto {

    @NotEmpty(message = "Este campo no puede estar en blanco.")
    @Size(min = 3, max = 128, message = "El nombre debe tener entre 3 y 128 caracteres")
    private String name;
}