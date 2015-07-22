package com.yairkukielka.slack.presentation.internal.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.yairkukielka.slack.data.cache.UserCache;
import com.yairkukielka.slack.data.cache.UserCacheImpl;
import com.yairkukielka.slack.data.executor.JobExecutor;
import com.yairkukielka.slack.data.net.UserApi;
import com.yairkukielka.slack.data.net.UserApiImpl;
import com.yairkukielka.slack.data.repository.UserDataRepository;
import com.yairkukielka.slack.domain.executor.PostExecutionThread;
import com.yairkukielka.slack.domain.executor.ThreadExecutor;
import com.yairkukielka.slack.domain.repository.UserRepository;
import com.yairkukielka.slack.presentation.AndroidApplication;
import com.yairkukielka.slack.presentation.UIThread;
import com.yairkukielka.slack.presentation.imageloader.ImageLoader;
import com.yairkukielka.slack.presentation.imageloader.PicassoImageLoader;
import com.yairkukielka.slack.presentation.internal.di.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    UserApi provideUserApi(UserApiImpl userApi) {
        return userApi;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Picasso providePicasso(OkHttpClient okHttpClient) {
        Picasso.Builder builder = new Picasso.Builder(this.application);
        //Picasso will use the okhttpClient
        builder.downloader(new OkHttpDownloader(okHttpClient));
        return builder.build();
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Picasso picasso) {
        return new PicassoImageLoader(picasso);
    }
}
