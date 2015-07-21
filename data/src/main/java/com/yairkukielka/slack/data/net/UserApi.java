package com.yairkukielka.slack.data.net;

import com.yairkukielka.slack.data.entity.UserEntity;
import com.yairkukielka.slack.domain.User;

import java.util.List;

import rx.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface UserApi {

    String API_BASE_URL = "https://slack.com/api/";
    /**
     * Authorization parameter name.
     */
    String TOKEN_PARAM_NAME = "token";

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link UserEntity}.
     */
    Observable<List<User>> getUserList();


    /**
     * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
     *
     * @param userId The user id used to get user data.
     */
//    Observable<User> getUserById(String userId);
}
