package com.yairkukielka.slack.presentation.adapter;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;

/**
 * Layout manager to position items inside a {@link androidx.recyclerview.widget.GridLayoutManager}.
 */
public class UsersGridLayoutManager extends GridLayoutManager {
    public UsersGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }
}
