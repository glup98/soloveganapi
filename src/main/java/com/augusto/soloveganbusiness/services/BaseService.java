package com.augusto.soloveganbusiness.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.BaseDto;
import com.augusto.soloveganbusiness.exceptions.ResourceNotFoundException;
import com.augusto.soloveganbusiness.mappers.IMapper;
import com.augusto.soloveganbusiness.repositories.BaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class BaseService<D extends BaseDto, E> {
    private final BaseRepository<E> baseRepository;
    private final IMapper<D, E> mapper;

    public D findById(Long id) {
        Optional<E> optional = baseRepository.findById(id);
        E entity = optional.orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
        return mapper.toDto(entity);
    }

    public List<D> findAll() {
        List<E> entities = baseRepository.findAll();
        return convertToDtoList(entities);
    }

    public D save(D dto) {
        E entity = mapper.toEntity(dto);
        entity = baseRepository.save(entity);
        return mapper.toDto(entity);
    }

    public List<D> convertToDtoList(List<E> entityList) {
        return entityList.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
