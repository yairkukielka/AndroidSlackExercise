package com.yairkukielka.slack.presentation.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yairkukielka.slack.presentation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * UserViewHolder, for the recyclerView
 */
public class UserViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name)
    TextView tv_name;
    @BindView(R.id.avatar)
    ImageView iv_avatar;

    public UserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
