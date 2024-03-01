package dev.lekha.cache;

import dev.lekha.cache.exception.NotFoundException;
import dev.lekha.cache.exception.StorageFullException;
import dev.lekha.cache.policies.EvictionPolicy;
import dev.lekha.cache.storage.Storage;

public class Cache<Key, Value> {
    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void put(Key key, Value value) {
        try {
            this.storage.put(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException e) {
            System.out.println("Storage got full");
            Key keyToRemove = evictionPolicy.evictKey();
            if(keyToRemove == null) {
                throw new RuntimeException("Unexpected state. Storage full, but no key to evict");
            }
            storage.remove(keyToRemove);
            put(key, value);
        }
    }

    public Value get(Key key) {
        try {
            Value value = this.storage.get(key);
            evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException e) {
            System.out.println("Tried to access non-existent key");
            return null;
        }
    }
}
