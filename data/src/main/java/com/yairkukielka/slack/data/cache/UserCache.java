package com.yairkukielka.slack.data.cache;


import com.yairkukielka.slack.data.entity.UserEntity;
import com.yairkukielka.slack.domain.User;

import java.util.List;

import rx.Observable;

/**
 * An interface representing a user Cache.
 */
public interface UserCache {
    /**
     * Gets an {@link rx.Observable} which will emit a {@link UserEntity}.
     *
     * @param userId The user id to retrieve data.
     */
    Observable<User> get(final String userId);

    /**
     * Puts elements into the cache.
     *
     * @param users Element to insert in the cache.
     */
    void put(List<User> users);

    /**
     * Checks if an element (User) exists in the cache.
     *
     * @param userId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final String userId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
