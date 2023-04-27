package com.augusto.soloveganbusiness.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseDto {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
