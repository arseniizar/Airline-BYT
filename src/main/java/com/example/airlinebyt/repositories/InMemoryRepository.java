package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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
        saveToFile();
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
        saveToFile();
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
        System.out.println("Attempting to load data from: " + storageFileName);
        File file = new File(storageFileName);
        if (file.exists() && file.length() > 0) {
            try {
                Map<Long, T> loadedExtent = objectMapper.readValue(file, typeReference);
                System.out.println("Deserialized " + storageFileName + ". loadedExtent is null: " + (loadedExtent == null));

                if (loadedExtent != null) {
                    // --- ПОЧАТОК ЗМІН ---
                    final AtomicLong maxId = new AtomicLong(0);

                    loadedExtent.forEach((id, entity) -> {
                        if (entity != null) {
                            extent.put(id, entity);
                            if (id > maxId.get()) {
                                maxId.set(id);
                            }
                        } else {
                            // ЦЕ НАЙВАЖЛИВІШИЙ ЛОГ!
                            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.err.println("WARNING: Found a null entity for ID: " + id + " in file: " + storageFileName);
                            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                    });

                    idCounter.set(maxId.get());
                    System.out.println("Successfully loaded " + extent.size() + " non-null entities from " + storageFileName);
                    // --- КІНЕЦЬ ЗМІН ---
                } else {
                    System.err.println("WARNING: Deserialization resulted in a null map for " + storageFileName);
                }
            } catch (IOException e) {
                System.err.println("ERROR loading from file: " + storageFileName);
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found or is empty, skipping: " + storageFileName);
        }
    }
}
