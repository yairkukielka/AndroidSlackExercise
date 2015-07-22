package com.yairkukielka.slack.presentation.view.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yairkukielka.slack.presentation.R;
import com.yairkukielka.slack.presentation.imageloader.ImageLoader;
import com.yairkukielka.slack.presentation.internal.di.components.UserComponent;
import com.yairkukielka.slack.presentation.model.UserModel;
import com.yairkukielka.slack.presentation.presenter.UserDetailsPresenter;
import com.yairkukielka.slack.presentation.view.UserDetailsView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows details of a certain user.
 */
public class UserDetailsFragment extends BaseFragment implements UserDetailsView {

    private static final String ARGUMENT_KEY_USER_ID = "ARGUMENT_USER_ID";
    @Inject
    UserDetailsPresenter userDetailsPresenter;
    @Inject
    ImageLoader imageLoader;
    @Bind(R.id.iv_cover)
    ImageView iv_cover;
    @Bind(R.id.tv_realname)
    TextView tv_realname;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.bt_retry)
    Button bt_retry;
    private String userId;

    public UserDetailsFragment() {
        super();
    }

    public static UserDetailsFragment newInstance(String userId) {
        UserDetailsFragment userDetailsFragment = new UserDetailsFragment();

        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putString(ARGUMENT_KEY_USER_ID, userId);
        userDetailsFragment.setArguments(argumentsBundle);

        return userDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.userDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userDetailsPresenter.destroy();
    }

    private void initialize() {
        this.getComponent(UserComponent.class).inject(this);
        this.userDetailsPresenter.setView(this);
        this.userId = getArguments().getString(ARGUMENT_KEY_USER_ID);
        this.userDetailsPresenter.initialize(this.userId);
    }

    @Override
    public void renderUser(UserModel user) {
        if (user != null) {
            Drawable niceCatPlaceholder = getResources().getDrawable(R.drawable.placeholder);
            this.imageLoader.load(user.getImage192(), iv_cover, niceCatPlaceholder);
            this.tv_realname.setText(user.getRealName());
            this.tv_name.setText(user.getName());
            this.tv_title.setText(user.getTitle());
        }
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    /**
     * Loads all users.
     */
    private void loadUserDetails() {
        if (this.userDetailsPresenter != null) {
            this.userDetailsPresenter.initialize(this.userId);
        }
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        UserDetailsFragment.this.loadUserDetails();
    }
}
