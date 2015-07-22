package com.yairkukielka.slack.domain.interactor;

import com.yairkukielka.slack.domain.executor.PostExecutionThread;
import com.yairkukielka.slack.domain.executor.ThreadExecutor;
import com.yairkukielka.slack.domain.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 */
public class GetUserDetailsUseCase extends UseCase {

    private final String userId;
    private final UserRepository userRepository;

    @Inject
    public GetUserDetailsUseCase(String userId, UserRepository userRepository,
                                 ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userId = userId;
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.getUser(this.userId);
    }
}
