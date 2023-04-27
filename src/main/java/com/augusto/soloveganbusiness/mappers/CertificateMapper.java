package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.augusto.soloveganbusiness.dto.CertificateDto;
import com.augusto.soloveganbusiness.models.Certificate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CertificateMapper implements IMapper<CertificateDto, Certificate> {

    private final ModelMapper modelMapper;

    @Override
    public Certificate toEntity(CertificateDto certificateDto) {
        Certificate certificate = modelMapper.map(certificateDto, Certificate.class);
        return certificate;
    }

    @Override
    public CertificateDto toDto(Certificate certificate) {
        CertificateDto certificateDto = modelMapper.map(certificate, CertificateDto.class);
        return certificateDto;
    }
}
