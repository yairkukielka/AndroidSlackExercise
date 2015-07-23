package com.yairkukielka.slack.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * Layout manager to position items inside a {@link android.support.v7.widget.RecyclerView}.
 */
public class UsersGridLayoutManager extends GridLayoutManager {
    public UsersGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }
}
