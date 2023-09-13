package com.github.savely03.bookingapp.mapper;

public interface BaseMapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);
}
