package com.yairkukielka.slack.presentation.internal.di.components;

import android.app.Activity;

import com.yairkukielka.slack.presentation.internal.di.PerActivity;
import com.yairkukielka.slack.presentation.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link com.yairkukielka.slack.presentation.internal.di.PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();

}
