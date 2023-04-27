package com.augusto.soloveganbusiness.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stores")
@NoArgsConstructor
@Getter
@Setter
public class Store extends BaseModel {

    @NotEmpty(message = "Este campo no puede estar en blanco.")
    @Size(min = 3, max = 128, message = "El nombre debe tener entre 3 y 128 caracteres")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
