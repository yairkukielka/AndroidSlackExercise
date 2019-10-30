package com.yairkukielka.slack.presentation.internal.di.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

import com.yairkukielka.slack.presentation.TransitionOptions;
import com.yairkukielka.slack.presentation.view.activity.UserDetailsActivity;
import com.yairkukielka.slack.presentation.view.activity.UserListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    /**
     * Goes to the user list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToUserList(Context context) {
        if (context != null) {
            Intent intentToLaunch = UserListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the user details screen.
     *
     * @param activity A Context needed to open the destiny activity.
     * @param userId User Id to see details.
     * @param options Activity transition options.
     */
    public void navigateToUserDetails(Activity activity, String userId, TransitionOptions options) {
        if (activity != null) {
            Intent intent = UserDetailsActivity.getCallingIntent(activity, userId);
            if (Build.VERSION.SDK_INT >= 21){
                Bundle bundle = null;
                if (options != null && options.getOptions() != null) {
                    bundle = options.getOptions().toBundle();
                }
                ActivityCompat.startActivity(activity, intent, bundle);
            } else{
                activity.startActivity(intent);
            }
        }
    }
}
