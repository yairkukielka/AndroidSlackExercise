package com.yairkukielka.slack.presentation.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yairkukielka.slack.presentation.R;

public class PicassoImageLoader implements ImageLoader {

    private Picasso picasso;

    public PicassoImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void load(String url, ImageView imageView) {
        picasso.load(url).placeholder(R.drawable.placeholder).fit().into(imageView);
    }

    @Override
    public void load(String url, ImageView imageView, Drawable placeholder) {
        picasso.load(url).placeholder(placeholder).into(imageView);
    }

    @Override
    public void loadWithoutEffects(String url, ImageView imageView) {
        picasso.load(url).noFade().noPlaceholder().into(imageView);
    }

    @Override
    public void loadCircular(String url, ImageView imageView, Drawable placeholder) {
        picasso.load(url).transform(new CircleTransform()).placeholder(placeholder).into(imageView);
    }
}
