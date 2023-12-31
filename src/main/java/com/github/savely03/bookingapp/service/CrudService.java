package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.entity.BaseEntity;
import com.github.savely03.bookingapp.exception.NotFoundException;
import com.github.savely03.bookingapp.mapper.mapstruct.BaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.data.repository.CrudRepository;

@RequiredArgsConstructor
public abstract class CrudService<E extends BaseEntity<I>, D, I> {

    private final CrudRepository<E, I> repository;
    private final NotFoundException notFoundException;
    private final BaseMapper<D, E> mapper;

    public D create(D dto) {
        E entity = mapper.toEntity(dto);
        entity.setId(null);
        return mapper.toDto(repository.save(entity));
    }

    public D update(I id, D dto) {
        try {
            E entity = mapper.toEntity(dto);
            entity.setId(id);
            return mapper.toDto(repository.save(entity));
        } catch (DbActionExecutionException e) {
            throw notFoundException;
        }
    }

    public D findById(I id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> notFoundException);
    }

    public void deleteById(I id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw notFoundException;
    }

    public boolean existsById(I id) {
        return repository.existsById(id);
    }
}
