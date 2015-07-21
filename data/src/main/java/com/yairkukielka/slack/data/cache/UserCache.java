package com.yairkukielka.slack.data.cache;


import com.yairkukielka.slack.domain.User;

import java.util.List;

import rx.Observable;

/**
 * An interface representing a user Cache.
 */
public interface UserCache {

    /**
     * Gets an {@link rx.Observable} which will emit the {@link User}s from the cache.
     */
    Observable<List<User>> getAll();

    /**
     * Gets an {@link rx.Observable} which will emit a {@link User}.
     *
     * @param userId The user id to retrieve data.
     */
    Observable<User> get(final String userId);

    /**
     * Evicts all elements and puts the new elements into the cache.
     *
     * @param users Elements to insert in the cache.
     */
    void evictAndPutAll(List<User> users);

    /**
     * Checks if an element (User) exists in the cache.
     *
     * @param userId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final String userId);

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
