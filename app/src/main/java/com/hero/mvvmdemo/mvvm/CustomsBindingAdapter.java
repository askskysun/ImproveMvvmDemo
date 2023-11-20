package com.hero.mvvmdemo.mvvm;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.hero.mvvmdemo.R;
import com.squareup.picasso.Picasso;

/**
 * <pre>
 * 自定义BindingAdapter
 * </pre>
 */
public class CustomsBindingAdapter {
    @BindingAdapter(value = {"image", "defaultImage"}, requireAll = false)
    public static void setImage(ImageView imageView, String imageUrl, int defaultImageResource) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageView);
        } else {
            imageView.setImageResource(defaultImageResource);
        }
    }
}