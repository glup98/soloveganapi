package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.lang.reflect.ParameterizedType;

@Component
public class GenericMapper<D, E> implements IMapper<D, E> {
    private final ModelMapper modelMapper;

    public GenericMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public D toDto(E entity) {
        return modelMapper.map(entity,
                (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public E toEntity(D dto) {
        return modelMapper.map(dto,
                (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }
}
