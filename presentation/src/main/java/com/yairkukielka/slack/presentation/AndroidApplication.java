package com.yairkukielka.slack.presentation;

import android.app.Application;

import com.yairkukielka.slack.presentation.internal.di.components.ApplicationComponent;
import com.yairkukielka.slack.presentation.internal.di.components.DaggerApplicationComponent;
import com.yairkukielka.slack.presentation.internal.di.modules.ApplicationModule;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
