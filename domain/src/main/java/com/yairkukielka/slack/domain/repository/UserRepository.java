package com.yairkukielka.slack.domain.repository;


import com.yairkukielka.slack.domain.User;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 */
public interface UserRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link User}.
     */
    Observable<List<User>> getUsers();

    /**
     * Get an {@link rx.Observable} which will emit a {@link User}.
     *
     * @param userId The user id used to retrieve user data.
     */
    Observable<User> getUser(final String userId);
}
