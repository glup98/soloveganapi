package com.augusto.soloveganbusiness.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.BaseDto;
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
        if (optional.isPresent()) {
            return mapper.toDto(optional.get());
        } else {
            return null;
        }
    }

    public List<D> findAll() {
        return baseRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public D save(D dto) {
        E entity = mapper.toEntity(dto);
        entity = baseRepository.save(entity);
        return mapper.toDto(entity);
    }
}
