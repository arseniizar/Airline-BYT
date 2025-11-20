package com.example.airlinebyt.controllers;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.services.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericCrudController<T extends BaseEntity, S extends GenericCrudService<T, ?>> {
    protected final S service;

    public GenericCrudController(S service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<T> create(@Valid @RequestBody T entity) {
        T createdEntity = service.create(entity);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    @GetMapping
    public List<T> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @Valid @RequestBody T entityDetails) {
        return service.update(id, entityDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
