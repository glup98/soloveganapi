package com.augusto.soloveganbusiness.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.repositories.BaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class BaseService<T> {
    private final BaseRepository<T> baseRepository;

    public T findById(Long id) {
        Optional<T> optional = baseRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public List<T> findAll() {
        return baseRepository.findAll();
    }

    public T save(T object) {
        return baseRepository.save(object);
    }
}
