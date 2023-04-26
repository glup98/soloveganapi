package com.augusto.soloveganbusiness.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Transient
    private String passwordConfirmation;
}
