package com.augusto.soloveganbusiness.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto extends BaseDto {
    @NotEmpty(message = "Este campo no puede estar en blanco.")
    private String categoryName;
}
