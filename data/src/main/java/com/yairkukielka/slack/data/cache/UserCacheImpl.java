package com.yairkukielka.slack.data.cache;

import android.content.Context;

import com.yairkukielka.slack.data.cache.serializer.JsonSerializer;
import com.yairkukielka.slack.domain.User;
import com.yairkukielka.slack.domain.executor.ThreadExecutor;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link UserCache} implementation.
 */
@Singleton
public class UserCacheImpl implements UserCache {

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link UserCacheImpl}.
     *
     * @param context             A
     * @param userCacheSerializer {@link JsonSerializer} for object serialization.
     * @param fileManager         {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    public UserCacheImpl(Context context, JsonSerializer userCacheSerializer,
                         FileManager fileManager, ThreadExecutor executor) {
        if (context == null || userCacheSerializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = userCacheSerializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override
    public synchronized Observable<User> get(final String userId) {
        return Observable.empty();
    }

    @Override
    public synchronized void put(List<User> users) {}

    @Override
    public boolean isCached(String userId) {
        return false;
    }

    @Override
    public boolean isExpired() {
        return true;
    }

    @Override
    public synchronized void evictAll() {}

}
