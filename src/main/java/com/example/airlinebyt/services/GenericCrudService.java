package com.example.airlinebyt.services;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.repositories.InMemoryRepository;
import java.util.List;
import java.util.Optional;

public abstract class GenericCrudService<T extends BaseEntity, R extends InMemoryRepository<T>> {

    protected final R repository;

    public GenericCrudService(R repository) {
        this.repository = repository;
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<T> update(Long id, T entityDetails) {
        if (repository.findById(id).isEmpty()) {
            return Optional.empty();
        }
        entityDetails.setId(id);
        return Optional.of(repository.save(entityDetails));
    }

    public boolean delete(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
