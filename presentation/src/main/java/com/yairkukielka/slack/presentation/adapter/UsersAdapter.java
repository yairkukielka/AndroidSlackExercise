package com.yairkukielka.slack.presentation.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yairkukielka.slack.presentation.R;
import com.yairkukielka.slack.presentation.TransitionOptions;
import com.yairkukielka.slack.presentation.imageloader.ImageLoader;
import com.yairkukielka.slack.presentation.model.UserModel;

import java.util.Collection;
import java.util.List;

/**
 * Adaptar that manages a collection of {@link UserModel}.
 */
public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private Activity activity;
    private ImageLoader imageLoader;
    private final LayoutInflater layoutInflater;
    private List<UserModel> usersCollection;
    private OnItemClickListener onItemClickListener;
    private final Resources resources;

    public UsersAdapter(Activity activity, ImageLoader imageLoader, Collection<UserModel> usersCollection) {
        this.activity = activity;
        this.resources = activity.getResources();
        this.imageLoader = imageLoader;
        this.validateUsersCollection(usersCollection);
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.usersCollection = (List<UserModel>) usersCollection;
    }

    @Override
    public int getItemCount() {
        return (this.usersCollection != null) ? this.usersCollection.size() : 0;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        final UserModel user = this.usersCollection.get(position);
        holder.tv_name.setText(user.getName());

        Drawable niceCatPlaceholder = resources.getDrawable(R.drawable.placeholder);
        this.imageLoader.load(user.getImage192(), holder.iv_avatar, niceCatPlaceholder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UsersAdapter.this.onItemClickListener != null) {
                    ActivityOptionsCompat activityOptions =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, holder.iv_avatar,
                                    UsersAdapter.this.resources.getString(R.string.transition_image));
                    TransitionOptions options = new TransitionOptions(activityOptions);
                    UsersAdapter.this.onItemClickListener.onUserItemClicked(user, options);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setUsersCollection(Collection<UserModel> usersCollection) {
        this.validateUsersCollection(usersCollection);
        this.usersCollection = (List<UserModel>) usersCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateUsersCollection(Collection<UserModel> usersCollection) {
        if (usersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public interface OnItemClickListener {
        void onUserItemClicked(UserModel userModel, TransitionOptions options);
    }

}
