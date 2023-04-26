package com.augusto.soloveganbusiness.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {
    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres")
    private String firstName;

    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
    @Size(min = 3, max = 30, message = "El apellido debe tener entre 3 y 30 caracteres")
    private String lastName;

    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
    @Email(message = "Por favor ingresa un email válido!")
    private String email;

    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
    @Size(min = 8, max = 128, message = "Contraseña debe tener entre 8 y 128 caracteres")
    private String password;
}
