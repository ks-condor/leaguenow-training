package com.kevinserrano.apps.leaguenow.utilities

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kevinserrano.apps.leaguenow.R

fun ImageView.bindImageUrl(
    url: String?, @DrawableRes placeholder: Int = R.drawable.ic_app_round,
    @DrawableRes errorPlaceholder: Int = R.drawable.ic_app_round,
    width: Int, height: Int
){
    if(url.isNullOrBlank()){
        setImageResource(placeholder)
        return
    }
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        //.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .skipMemoryCache(true)
        .error(errorPlaceholder)
        .apply(RequestOptions().override(width, height))
        .into(this)
}


fun ImageView.bindImageUrl(
    url: String?, @DrawableRes placeholder: Int = R.drawable.ic_app_round,
    @DrawableRes errorPlaceholder: Int = R.drawable.ic_app_round
){
    if(url.isNullOrBlank()){
        setImageResource(placeholder)
        return
    }
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        //.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .skipMemoryCache(true)
        .error(errorPlaceholder)
        .into(this)
}



fun ImageView.bindRoundedImageUrl(
    url: String?, @DrawableRes placeholder: Int = R.drawable.ic_app_round,
    @DrawableRes errorPlaceholder: Int = R.drawable.ic_app_round,
    width: Int = 400, height: Int = 300, radius: Int = 16
){
    if(url.isNullOrBlank()){
        setImageResource(placeholder)
        return
    }
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        //.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .skipMemoryCache(true)
        .error(errorPlaceholder)
        .apply(RequestOptions().override(width, height)
            .transform(CenterCrop(), RoundedCorners(radius)))
        .into(this)
}

fun ImageView.bindCircleImageUrl(
    url: String?, @DrawableRes placeholder: Int = R.drawable.ic_app_round,
    @DrawableRes errorPlaceholder: Int = R.drawable.ic_app_round
){
    if(url.isNullOrBlank()){
        setImageResource(placeholder)
        return
    }
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        //.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .skipMemoryCache(true)
        .error(errorPlaceholder)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}



