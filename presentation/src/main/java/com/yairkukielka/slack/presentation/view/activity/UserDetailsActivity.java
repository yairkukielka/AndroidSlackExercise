package com.yairkukielka.slack.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.yairkukielka.slack.presentation.R;
import com.yairkukielka.slack.presentation.internal.di.HasComponent;
import com.yairkukielka.slack.presentation.internal.di.components.DaggerUserComponent;
import com.yairkukielka.slack.presentation.internal.di.components.UserComponent;
import com.yairkukielka.slack.presentation.internal.di.modules.UserModule;
import com.yairkukielka.slack.presentation.view.fragment.UserDetailsFragment;


/**
 * Activity that shows details of a certain user.
 */
public class UserDetailsActivity extends BaseActivity implements HasComponent<UserComponent> {

    private static final String INTENT_EXTRA_PARAM_USER_ID = "INTENT_PARAM_USER_ID";
    private static final String INSTANCE_STATE_PARAM_USER_ID = "STATE_PARAM_USER_ID";

    private String userId;
    private UserComponent userComponent;

    public static Intent getCallingIntent(Context context, String userId) {
        Intent callingIntent = new Intent(context, UserDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);

        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        this.initializeActivity(savedInstanceState);
        this.initializeInjector();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INSTANCE_STATE_PARAM_USER_ID, this.userId);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.userId = getIntent().getStringExtra(INTENT_EXTRA_PARAM_USER_ID);
            addFragment(R.id.fl_fragment, UserDetailsFragment.newInstance(this.userId));
        } else {
            this.userId = savedInstanceState.getString(INSTANCE_STATE_PARAM_USER_ID);
        }
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule(this.userId))
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }
}
