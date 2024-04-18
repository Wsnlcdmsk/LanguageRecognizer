package com.project.langrecognizer.cache;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Simple cache implementation using a LinkedHashMap.
 * @param <T> Type of cached objects.
 * @param <I> Type of cache keys.
 */
@NoArgsConstructor
@Component
public class Cache<T, I> {
    /** The maximum size of the cache. */
    private static final int CACHE_SIZE = 1000;

    /** Internal map to store cached objects. */
    private final Map<I, T> map = new LinkedHashMap<>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<I, T> eldest) {
            return size() > CACHE_SIZE;
        }
    };

    /**
     * Retrieves a cached object by its identifier.
     * @param id The identifier of the cached object.
     * @return An Optional containing the cached object, or empty if not found.
     */
    public Optional<T> getCachedById(I id) {
        return Optional.ofNullable(map.get(id));
    }

    /**
     * Deletes a cached object by its identifier.
     * @param id The identifier of the cached object to delete.
     */
    public void deleteCachedById(I id) {
        map.remove(id);
    }

    /**
     * Saves a new cached object or updates an existing one.
     * @param id The identifier of the cached object.
     * @param entity The cached object to save or update.
     * @param <S> The type of the cached object.
     * @return The saved or updated cached object.
     */
    public <S extends T> S saveCached(I id, S entity) {
        return (S) map.put(id, entity);
    }
}
