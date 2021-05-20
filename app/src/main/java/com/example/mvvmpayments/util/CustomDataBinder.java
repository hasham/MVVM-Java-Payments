package com.example.mvvmpayments.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class CustomDataBinder {
    private CustomDataBinder() {
        //NO-OP
    }

    @BindingAdapter("setImage")
    public static void setImage(ImageView imageView, String url) {

        if (url != null && !url.equals("")) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
        } else {
            // show error image
        }
    }
}
