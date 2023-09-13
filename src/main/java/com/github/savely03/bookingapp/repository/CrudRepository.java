package com.github.savely03.bookingapp.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<I, T> {

    T create(T obj);

    Optional<T> findById(I id);

    List<T> findAll();

    Boolean exists(I id);
}
