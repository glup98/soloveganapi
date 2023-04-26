package com.augusto.soloveganbusiness.mappers;

public interface IMapper<I, O> {
    public O mapEntity(I in);

    public I mapDto(O in);
}
