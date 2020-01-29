package com.norm.countries.utils

import android.graphics.drawable.PictureDrawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.norm.countries.R
import com.norm.countries.network.Status
import com.norm.countries.utils.svg.GlideApp

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.norm.countries.utils.svg.SvgSoftwareLayerSetter


/**
 * Create by Kyaw Zayar Tun on 28/01/2020.
 */
@BindingAdapter("loadingVisibility")
fun bindLoadingStatus(progress: ProgressBar, status: Status?) {
    when (status) {
        Status.LOADING -> {
            progress.visibility = View.VISIBLE
        }
        Status.SUCCESS -> {
            progress.visibility = View.GONE
        }
        Status.ERROR -> {
            progress.visibility = View.GONE
        }
    }
}

@BindingAdapter("imgVisibility")
fun bindStatus(imageView: ImageView, status: Status?) {
    when (status) {
        Status.LOADING -> {
            imageView.visibility = View.GONE
        }
        Status.SUCCESS -> {
            imageView.visibility = View.GONE
        }
        Status.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImageFromUrl(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.image_loading)
//                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("svgImageUrl")
fun bindSvgImageFromUrl(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        GlideApp.with(imageView.context)
            .`as`(PictureDrawable::class.java)
            .load(imgUri)
            .placeholder(R.drawable.image_loading)
            .error(R.drawable.image_error)
            .transition(withCrossFade())
            .listener(SvgSoftwareLayerSetter())
            .into(imageView)
    }
}