package com.github.savely03.bookingapp.restcontroller;

import com.github.savely03.bookingapp.entity.BaseEntity;
import com.github.savely03.bookingapp.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public abstract class CrudController<E extends BaseEntity<I>, D, I> {

    private final CrudService<E, D, I> crudService;

    @PostMapping
    public ResponseEntity<D> create(D dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(crudService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable I id, D dto) {
        return ResponseEntity.ok(crudService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable I id) {
        return ResponseEntity.ok(crudService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable I id) {
        crudService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
