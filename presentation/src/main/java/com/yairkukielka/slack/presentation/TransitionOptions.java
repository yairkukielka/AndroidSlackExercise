package com.yairkukielka.slack.presentation;

import android.support.v4.app.ActivityOptionsCompat;

/**
 * Transition options to navigate between views
 */
public class TransitionOptions {

    private ActivityOptionsCompat options;

    public TransitionOptions(ActivityOptionsCompat options) {
        this.options = options;
    }

    public ActivityOptionsCompat getOptions() {
        return options;
    }

    public void setOptions(ActivityOptionsCompat options) {
        this.options = options;
    }
}
