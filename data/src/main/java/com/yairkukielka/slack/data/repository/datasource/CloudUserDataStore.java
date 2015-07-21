package com.yairkukielka.slack.data.repository.datasource;

import com.yairkukielka.slack.data.cache.UserCache;
import com.yairkukielka.slack.data.net.UserApi;
import com.yairkukielka.slack.domain.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
@Singleton
public class CloudUserDataStore implements UserDataStore {

    private final UserApi userApi;
    private final UserCache userCache;

    /**
     * Construct a {@link UserDataStore} based on connections to the api (Cloud).
     *
     * @param userApi   The {@link UserApi} implementation to use.
     * @param userCache A {@link UserCache} to cache data retrieved from the api.
     */
    @Inject
    public CloudUserDataStore(UserApi userApi, UserCache userCache) {
        this.userApi = userApi;
        this.userCache = userCache;
    }

    @Override
    public Observable<List<User>> getUserEntityList() {
        return this.userApi.getUserList();
                //TODO save to disk .doOnNext(users -> CloudUserDataStore.this.userCache.put(users));
    }

//    @Override
//    public Observable<UserEntity> getUserEntityDetails(final String userId) {
//        return this.userApi.getUserEntityById(userId);
//    }
}
