package com.yairkukielka.slack.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.yairkukielka.slack.presentation.R;
import com.yairkukielka.slack.presentation.TransitionOptions;
import com.yairkukielka.slack.presentation.internal.di.HasComponent;
import com.yairkukielka.slack.presentation.internal.di.components.DaggerUserComponent;
import com.yairkukielka.slack.presentation.internal.di.components.UserComponent;
import com.yairkukielka.slack.presentation.model.UserModel;
import com.yairkukielka.slack.presentation.view.fragment.UserListFragment;

/**
 * Activity that shows a list of Users.
 */
public class UserListActivity extends BaseActivity implements HasComponent<UserComponent>,
        UserListFragment.UserListListener {

    private UserComponent userComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, UserListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        this.initializeInjector();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        }
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    @Override
    public void onUserClicked(UserModel userModel, TransitionOptions options) {
        this.navigator.navigateToUserDetails(this, userModel.getId(), options);
    }
}
