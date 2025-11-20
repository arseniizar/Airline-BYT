package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.BaseEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Generic in-memory repository to manage class extent and persistence to a JSON file.
 * @param <T> The type of the entity, which must extend BaseEntity.
 */
public abstract class InMemoryRepository<T extends BaseEntity> {

    protected final Map<Long, T> extent = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    private final String storageFileName;
    private final ObjectMapper objectMapper;
    private final TypeReference<Map<Long, T>> typeReference;

    public InMemoryRepository(Class<T> entityClass, TypeReference<Map<Long, T>> typeReference) {
        this.storageFileName = "data/" + entityClass.getSimpleName().toLowerCase() + "s.json";
        this.typeReference = typeReference;

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());

        loadFromFile();
    }

    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(idCounter.incrementAndGet());
        }
        extent.put(entity.getId(), entity);
        saveToFile(); // Persist changes
        return entity;
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(extent.get(id));
    }

    public List<T> findAll() {
        return new ArrayList<>(extent.values());
    }

    public void deleteById(Long id) {
        extent.remove(id);
        saveToFile(); // Persist changes
    }

    public long count() {
        return extent.size();
    }

    private void saveToFile() {
        try {
            File file = new File(storageFileName);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, extent);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + storageFileName);
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        File file = new File(storageFileName);
        if (file.exists() && file.length() > 0) {
            try {
                Map<Long, T> loadedExtent = objectMapper.readValue(file, typeReference);
                extent.putAll(loadedExtent);
                long maxId = loadedExtent.keySet().stream().max(Long::compare).orElse(0L);
                idCounter.set(maxId);
            } catch (IOException e) {
                System.err.println("Error loading from file: " + storageFileName);
                e.printStackTrace();
            }
        }
    }
}