package com.yairkukielka.slack.presentation.internal.di.modules;


import com.yairkukielka.slack.domain.executor.PostExecutionThread;
import com.yairkukielka.slack.domain.executor.ThreadExecutor;
import com.yairkukielka.slack.domain.interactor.GetUserDetailsUseCase;
import com.yairkukielka.slack.domain.interactor.GetUserListUseCase;
import com.yairkukielka.slack.domain.interactor.UseCase;
import com.yairkukielka.slack.domain.repository.UserRepository;
import com.yairkukielka.slack.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {

    private String userId = "_";

    public UserModule() {
    }

    public UserModule(String userId) {
        this.userId = userId;
    }

    @Provides
    @PerActivity
    @Named("userList")
    UseCase provideGetUserListUseCase(
            GetUserListUseCase getUserListUseCase) {
        return getUserListUseCase;
    }

    @Provides
    @PerActivity
    @Named("userDetails")
    UseCase provideGetUserDetailsUseCase(
            UserRepository userRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new GetUserDetailsUseCase(userId, userRepository, threadExecutor, postExecutionThread);
    }
}