package com.system.platform.controllers;


import com.system.platform.services.ICrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class CrudController<E, C, R> {
    protected abstract ICrudService<E, C> getService();
    protected abstract R toResponse(E entity);

    @PostMapping
    public ResponseEntity<R> create(@RequestBody C data) {
        E entity = this.getService().create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                toResponse(entity)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<R> getById(@PathVariable UUID id) {
        E entity = getService().readById(id);
        return ResponseEntity.ok(toResponse(entity));
    }

    @GetMapping
    public ResponseEntity<List<R>> getAll() {
        List<R> lista = getService().readAll()
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        getService().deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
