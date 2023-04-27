package com.augusto.soloveganbusiness.mappers;

public interface IMapper<D, E> {
    D toDto(E entity);

    E toEntity(D dto);
}
