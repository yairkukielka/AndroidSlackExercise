package com.yairkukielka.slack.data.repository.datasource;

import com.yairkukielka.slack.data.cache.UserCache;
import com.yairkukielka.slack.domain.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link UserDataStore} implementation based on file system data store.
 */
@Singleton
public class DiskUserDataStore implements UserDataStore {

    private final UserCache userCache;

    /**
     * Construct a {@link UserDataStore} based file system data store.
     *
     * @param userCache A {@link UserCache} to cache data retrieved from the api.
     */
    @Inject
    public DiskUserDataStore(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public Observable<List<User>> getUserEntityList() {
        return Observable.empty();
    }

}
