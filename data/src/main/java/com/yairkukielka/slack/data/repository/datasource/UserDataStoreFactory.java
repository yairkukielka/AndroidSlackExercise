package com.yairkukielka.slack.data.repository.datasource;

import android.content.Context;

import com.yairkukielka.slack.data.cache.UserCache;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton
public class UserDataStoreFactory {

    private final Context context;
    private final UserCache userCache;
    CloudUserDataStore cloudUserDataStore;
    DiskUserDataStore diskUserDataStore;

    @Inject
    public UserDataStoreFactory(Context context, UserCache userCache, CloudUserDataStore cloudUserDataStore, DiskUserDataStore diskUserDataStore) {
        if (context == null || userCache == null || cloudUserDataStore == null || diskUserDataStore == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userCache = userCache;
        this.cloudUserDataStore = cloudUserDataStore;
        this.diskUserDataStore = diskUserDataStore;
    }

    /**
     * Create {@link UserDataStore} from a user id.
     */
    public UserDataStore create(String userId) {
        UserDataStore userDataStore;

        if (this.userCache.isCached(userId)) {
            userDataStore = diskUserDataStore;
        } else {
            userDataStore = cloudUserDataStore;
        }

        return userDataStore;
    }

    /**
     * Create {@link UserDataStore} to retrieve data from the Cloud.
     */
    public UserDataStore getCloudDataStore() {
        return cloudUserDataStore;
    }

    /**
     * Create {@link UserDataStore} to retrieve data from the Cloud.
     */
    public UserDataStore getDiskDataStore() {
        return diskUserDataStore;
    }
}