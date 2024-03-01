package dev.lekha.cache.storage;

import dev.lekha.cache.exception.NotFoundException;
import dev.lekha.cache.exception.StorageFullException;

public interface Storage<Key, Value> {
    void put(Key key, Value value) throws StorageFullException;
    Value get(Key key) throws NotFoundException;
    void remove(Key key) throws NotFoundException;
}
