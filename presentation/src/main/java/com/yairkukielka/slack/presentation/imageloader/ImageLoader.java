package com.yairkukielka.slack.presentation.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public interface ImageLoader {
  void load(String url, ImageView imageView);
  void load(String url, ImageView imageView, Drawable placeholder);
  void loadWithoutEffects(String url, ImageView imageView);
  void loadCircular(String url, ImageView imageView, Drawable placeholder);
}
