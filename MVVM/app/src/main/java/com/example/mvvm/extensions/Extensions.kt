package com.example.mvvm.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class MyBindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUsingURL(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}