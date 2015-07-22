package com.yairkukielka.slack.presentation.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoImageLoader implements ImageLoader {

    private Picasso picasso;

    public PicassoImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void load(String url, ImageView imageView) {
        picasso.load(url).fit().into(imageView);
    }

    @Override
    public void load(String url, ImageView imageView, Drawable placeholder) {
        picasso.load(url).placeholder(placeholder).fit().into(imageView);
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
