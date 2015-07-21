package com.yairkukielka.slack.data.repository;

import com.yairkukielka.slack.data.repository.datasource.UserDataStore;
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
        // Based on this pattern http://blog.danlew.net/2015/06/22/loading-data-from-multiple-sources-with-rxjava/
        return Observable.concat(
                userDataStoreFactory.getDiskDataStore().getUserList()
                        .doOnNext(n ->System.out.println("onNextDisk")).doOnCompleted(() ->System.out.println("onCompDisk")),
                userDataStoreFactory.getCloudDataStore().getUserList()
                        .doOnNext(n -> System.out.println("onNextCloud")).doOnCompleted(() -> System.out.println("onCompCloud")))
                .first()
                .doOnNext(
                        n -> System.out.println("doOnNext size=" + n.size()))
                .doOnCompleted(
                        () -> System.out.println("doOnCompleted"));
//        final UserDataStore userDataStore = this.userDataStoreFactory.getCloudDataStore();
//        return userDataStore.getUserEntityList();
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<User> getUser(String userId) {
        final UserDataStore userDataStore = this.userDataStoreFactory.create(userId);
        return userDataStore.getUserDetails(userId);
    }
}
