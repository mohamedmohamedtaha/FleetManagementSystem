package com.example.fleetmanagement.ui.home.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.fleetmanagement.R
@BindingAdapter("profileImage")
fun imageTransformProfile(imageView: ImageView, url: Any) {
    Glide.with(imageView.context).load(url)
        .placeholder(R.drawable.ic_user)
        .apply(
            RequestOptions.bitmapTransform(
                MultiTransformation(
                    CenterCrop(),
                    RoundedCorners(10)
                )
            )
        ).into(imageView)

}

@BindingAdapter("imageUrl")
fun taskImage(imageView: ImageView, url: Any) {
    Glide.with(imageView.context).load("https://cars.mutsaweq.com/images/$url")
        .placeholder(R.mipmap.fleet_logo)
        .apply(RequestOptions.bitmapTransform(MultiTransformation(RoundedCorners(18))))
        .into(imageView)
}













