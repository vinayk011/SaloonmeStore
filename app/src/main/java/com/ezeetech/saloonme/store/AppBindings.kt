/*
 *  Created by Vinay on 2-2-2021 for EzeeTech.
 *  Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("app:load_Image_uri")
fun loadPoster(view: ImageView, url: String?) {
    Glide.with(view.context)
            .load(url)
            .into(view)
}

@BindingAdapter("app:image_uri", "app:default_image")
fun loadImage(view: ImageView, url: String?, defaultImage: Drawable) {
    if (url == null) {
        view.setImageDrawable(defaultImage)
    } else {
        Glide.with(view.context)
                .load(url)
                .into(view)
    }
}

@BindingAdapter("app:reviews")
fun setReviews(view: TextView, reviews: Int) {
    view.text = "($reviews)"
}

@BindingAdapter("app:place", "app:category")
fun setPlaceAndCategory(view: TextView, place: String, category: String) {
    view.text = "$place | $category"
}

@BindingAdapter("app:password_error")
fun setPasswordError(inputLayout: TextInputLayout, error: String?) {
    inputLayout.error = error
}

