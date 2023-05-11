package com.augusto.soloveganbusiness.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseModel {
    @NotEmpty(message = "Este campo no puede estar en blanco.")
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres")
    private String firstName;

    @NotEmpty(message = "Este campo no puede estar en blanco.")
    @Size(min = 3, max = 30, message = "El apellido debe tener entre 3 y 30 caracteres")
    private String lastName;

    @NotEmpty(message = "Este campo no puede estar en blanco.")
    @Email(message = "Por favor ingresa un email válido!")
    private String email;

    @NotEmpty(message = "Este campo no puede estar en blanco.")
    @Size(min = 8, max = 128, message = "Contraseña debe tener entre 8 y 128 caracteres")
    private String password;

    @Transient
    private String passwordConfirmation;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Store> stores;
}
