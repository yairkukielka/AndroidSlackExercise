package com.yairkukielka.slack.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.yairkukielka.slack.presentation.R;
import com.yairkukielka.slack.presentation.adapter.UsersAdapter;
import com.yairkukielka.slack.presentation.adapter.UsersGridLayoutManager;
import com.yairkukielka.slack.presentation.imageloader.ImageLoader;
import com.yairkukielka.slack.presentation.internal.di.components.UserComponent;
import com.yairkukielka.slack.presentation.model.UserModel;
import com.yairkukielka.slack.presentation.presenter.UserListPresenter;
import com.yairkukielka.slack.presentation.view.UserListView;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a list of Users.
 */
public class UserListFragment extends BaseFragment implements UserListView {

    @Inject
    UserListPresenter userListPresenter;
    @Inject
    ImageLoader imageLoader;
    @Bind (R.id.users_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.rv_users)
    RecyclerView rv_users;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.bt_retry)
    Button bt_retry;
    private UsersAdapter usersAdapter;
    private RecyclerView.LayoutManager usersLayoutManager;
    private UserListListener userListListener;
    private UsersAdapter.OnItemClickListener onItemClickListener =
            new UsersAdapter.OnItemClickListener() {
                @Override
                public void onUserItemClicked(UserModel userModel) {
                    if (UserListFragment.this.userListPresenter != null && userModel != null) {
                        UserListFragment.this.userListPresenter.onUserClicked(userModel);
                    }
                }
            };

    public UserListFragment() {
        super();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof UserListListener) {
            this.userListListener = (UserListListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, true);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
        setupUI();
        this.loadUserList();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.userListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userListPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userListPresenter.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initialize() {
        this.getComponent(UserComponent.class).inject(this);
        this.userListPresenter.setView(this);
    }

    private void setupUI() {
        this.rv_users.setItemAnimator(new DefaultItemAnimator());
        this.usersLayoutManager = new UsersGridLayoutManager(getActivity(),
                getResources().getInteger(R.integer.users_grid_span_count));
        this.rv_users.setLayoutManager(usersLayoutManager);

        this.usersAdapter = new UsersAdapter(getActivity(), imageLoader, new ArrayList<UserModel>());
        this.usersAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_users.setAdapter(usersAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUserList();
            }
        });
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
        mSwipeRefreshLayout.setRefreshing(false);
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
    public void renderUserList(Collection<UserModel> userModelCollection) {
        if (userModelCollection != null) {
            this.usersAdapter.setUsersCollection(userModelCollection);
        }
    }

    @Override
    public void viewUser(UserModel userModel) {
        if (this.userListListener != null) {
            this.userListListener.onUserClicked(userModel);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context getContext() {
        return this.getActivity().getApplicationContext();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        this.userListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        UserListFragment.this.loadUserList();
    }

    /**
     * Interface for listening user list events.
     */
    public interface UserListListener {
        void onUserClicked(final UserModel userModel);
    }

}
