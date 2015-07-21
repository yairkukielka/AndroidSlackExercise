package com.yairkukielka.slack.data.net;

import com.yairkukielka.slack.data.entity.SlackResponseUserList;
import com.yairkukielka.slack.data.entity.UserEntity;

import javax.inject.Singleton;

import retrofit.http.GET;
import rx.Observable;

/**
 * RestApi for retrieving data from the network.
 */
@Singleton
public interface SlackRestAdapter {

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link SlackResponseUserList}.
     */
    @GET("/users.list")
    Observable<SlackResponseUserList> getUserEntityList();


    /**
     * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
     *
     * @param userId The user id used to get user data.
     */
//    @GET("/users.info")
//    Observable<SlackResponseUser> getUserEntityById(@Query("user") String userId);

}
