package com.augusto.soloveganbusiness.dto;

import javax.validation.constraints.NotEmpty;

public class CertificateDto extends BaseDto {
    @NotEmpty(message = "Este campo no puede estar en blanco.")
    private String typeCertificate;
}
