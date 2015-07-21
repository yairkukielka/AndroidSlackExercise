package com.yairkukielka.slack.data.repository.datasource;


import com.yairkukielka.slack.data.entity.UserEntity;
import com.yairkukielka.slack.domain.User;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface UserDataStore {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link UserEntity}.
     */
    Observable<List<User>> getUserEntityList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link UserEntity} by its id.
     *
     * @param userId The id to retrieve user data.
     */
    //Observable<User> getUserEntityDetails(final String userId);
}
