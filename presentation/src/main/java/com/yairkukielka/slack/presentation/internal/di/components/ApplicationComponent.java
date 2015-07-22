package com.yairkukielka.slack.presentation.internal.di.components;

import android.content.Context;

import com.yairkukielka.slack.domain.executor.PostExecutionThread;
import com.yairkukielka.slack.domain.executor.ThreadExecutor;
import com.yairkukielka.slack.domain.repository.UserRepository;
import com.yairkukielka.slack.presentation.imageloader.ImageLoader;
import com.yairkukielka.slack.presentation.internal.di.modules.ApplicationModule;
import com.yairkukielka.slack.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    UserRepository userRepository();

    ImageLoader imageLoader();
}
