package com.yairkukielka.slack.data.repository;

import com.yairkukielka.slack.data.repository.datasource.UserDataStoreFactory;
import com.yairkukielka.slack.domain.User;
import com.yairkukielka.slack.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory userDataStoreFactory;

    /**
     * Constructs a {@link UserRepository}.
     *
     * @param dataStoreFactory     A factory to construct different data source implementations.
     */
    @Inject
    public UserDataRepository(UserDataStoreFactory dataStoreFactory) {
        this.userDataStoreFactory = dataStoreFactory;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<List<User>> getUsers() {

        return Observable.concat(
                userDataStoreFactory.getDiskDataStore().getUserEntityList(),
                userDataStoreFactory.getCloudDataStore().getUserEntityList())
                .first();
//        final UserDataStore userDataStore = this.userDataStoreFactory.getCloudDataStore();
//        return userDataStore.getUserEntityList();
    }

//    @SuppressWarnings("Convert2MethodRef")
//    @Override
//    public Observable<User> getUser(String userId) {
//        final UserDataStore userDataStore = this.userDataStoreFactory.create(userId);
//        return userDataStore.getUserEntityDetails(userId)
//                .map(userEntity -> this.userEntityDataMapper.transform(userEntity));
//    }
}
