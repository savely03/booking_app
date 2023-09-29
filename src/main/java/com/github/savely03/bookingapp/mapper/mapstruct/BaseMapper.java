package com.github.savely03.bookingapp.mapper.mapstruct;

public interface BaseMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);
}
