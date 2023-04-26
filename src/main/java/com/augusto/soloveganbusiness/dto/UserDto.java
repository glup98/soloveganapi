package com.augusto.soloveganbusiness.dto;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirmation;
}
