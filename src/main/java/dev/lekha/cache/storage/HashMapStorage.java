package dev.lekha.cache.storage;

import dev.lekha.cache.exception.NotFoundException;
import dev.lekha.cache.exception.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<Key, Value> implements Storage<Key, Value>{

    Map<Key, Value> storage;
    private final Integer capacity;

    public HashMapStorage(Integer capacity) {
        this.capacity = capacity;
        storage = new HashMap<>();
    }

    @Override
    public void put(Key key, Value value) throws StorageFullException {
        if (isStorageFull()) throw new StorageFullException("Capacity Full.....");
        System.out.println("Lekha, adding key: " + key + "; Value: " + value);
        storage.put(key, value);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + "doesn't exist in cache.");
        System.out.println("Lekha, getting key: " + key);
        return storage.get(key);
    }

    @Override
    public void remove(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + "doesn't exist in cache.");
        System.out.println("Lekha, removing key: " + key);
        storage.remove(key);
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}
