package dev.lekha;

import dev.lekha.cache.Cache;
import dev.lekha.cache.factories.CacheFactory;

 public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Cache<Integer, Integer> cache = new CacheFactory<Integer, Integer>().defaultCache(3);

        cache.put(1, 1);
        cache.put(2, 3);
    }
}