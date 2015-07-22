package com.yairkukielka.slack.presentation.internal.di.components;

import com.yairkukielka.slack.presentation.internal.di.PerActivity;
import com.yairkukielka.slack.presentation.internal.di.modules.ActivityModule;
import com.yairkukielka.slack.presentation.internal.di.modules.UserModule;
import com.yairkukielka.slack.presentation.view.fragment.UserDetailsFragment;
import com.yairkukielka.slack.presentation.view.fragment.UserListFragment;

import dagger.Component;

/**
 * A scope {@link com.yairkukielka.slack.presentation.internal.di.PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
    void inject(UserListFragment userListFragment);

    void inject(UserDetailsFragment userDetailsFragment);
}
