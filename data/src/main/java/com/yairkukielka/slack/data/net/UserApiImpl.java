package com.yairkukielka.slack.data.net;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.yairkukielka.data.BuildConfig;
import com.yairkukielka.slack.data.entity.SlackResponseUser;
import com.yairkukielka.slack.data.entity.SlackResponseUserList;
import com.yairkukielka.slack.data.entity.UserEntity;
import com.yairkukielka.slack.data.entity.mapper.UserEntityDataMapper;
import com.yairkukielka.slack.data.exception.NetworkConnectionException;
import com.yairkukielka.slack.domain.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import rx.Observable;
import rx.functions.Func1;

/**
 * UserApi for retrieving data from the network.
 */
@Singleton
public class UserApiImpl implements UserApi {

    // TODO move to gradle.properties
    private static final String AUTH_TOKEN = BuildConfig.AUTH_TOKEN;
    private SlackRestAdapter slackRestAdapter;
    private final UserEntityDataMapper userEntityDataMapper;

    /**
     * Constructor
     *
     * @param gson                 gson parser.
     * @param userEntityDataMapper {@link UserEntityDataMapper}.
     */
    @Inject
    public UserApiImpl(Gson gson, OkHttpClient okhttpClient, UserEntityDataMapper userEntityDataMapper) {
        this.userEntityDataMapper = userEntityDataMapper;

        RestAdapter.LogLevel logLevel = BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(UserApi.API_BASE_URL)
                .setLogLevel(logLevel)
                .setRequestInterceptor(request -> request.addQueryParam(UserApi.TOKEN_PARAM_NAME, AUTH_TOKEN))
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okhttpClient))
                .build();

        slackRestAdapter = restAdapter.create(SlackRestAdapter.class);
    }

    /**
     * Function that transforms a SlackResponseUserList to a List<User>
     */
    private final Func1<SlackResponseUserList, List<User>> transformResponseToUserList =
            slackResponseUserList -> {
                List<UserEntity> members = slackResponseUserList.getMembers();
                if (members != null && !members.isEmpty()) {
                    return UserApiImpl.this.userEntityDataMapper.transform(members);
                }
                return null;
            };

    /**
     * Function that transforms a SlackResponseUser to a User
     */
    private final Func1<SlackResponseUser, User> transformResponseToUser =
            slackResponseUser -> {
                if (slackResponseUser.getUser() != null) {
                    return UserApiImpl.this.userEntityDataMapper.transform(slackResponseUser.getUser());
                }
                return null;
            };

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link UserEntity}.
     */
    @Override
    public Observable<List<User>> getUserList() {
        return slackRestAdapter.getUserEntityList()
                // if error, throw NetworkConnectionException
                .onErrorResumeNext(Observable.error(new NetworkConnectionException()))
                .map(transformResponseToUserList);
    }


    /**
     * Retrieves an {@link Observable} which will emit a {@link User}.
     *
     * @param userId The user id used to get user data.
     */
    @Override
    public Observable<User> getUserById(String userId) {
        return slackRestAdapter.getUserById(userId)
                // if error, throw NetworkConnectionException
                .onErrorResumeNext(Observable.error(new NetworkConnectionException()))
                .map(transformResponseToUser);
    }
}
