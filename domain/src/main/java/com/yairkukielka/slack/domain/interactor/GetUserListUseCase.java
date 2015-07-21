package com.yairkukielka.slack.domain.interactor;

import com.yairkukielka.slack.domain.executor.PostExecutionThread;
import com.yairkukielka.slack.domain.executor.ThreadExecutor;
import com.yairkukielka.slack.domain.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link User}.
 */
public class GetUserListUseCase extends UseCase {

    private final UserRepository userRepository;

    @Inject
    public GetUserListUseCase(UserRepository userRepository, ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.userRepository.getUsers();
    }
}
