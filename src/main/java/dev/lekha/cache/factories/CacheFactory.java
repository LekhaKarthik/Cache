package dev.lekha.cache.factories;

import dev.lekha.cache.Cache;
import dev.lekha.cache.policies.LRUEvictionPolicy;
import dev.lekha.cache.storage.HashMapStorage;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<Key, Value>(new LRUEvictionPolicy<Key>(),
                new HashMapStorage<Key, Value>(capacity));
    }
}
