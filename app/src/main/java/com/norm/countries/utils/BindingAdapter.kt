package com.norm.countries.utils

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.norm.countries.network.Status

/**
 * Create by Kyaw Zayar Tun on 28/01/2020.
 */
@BindingAdapter("loadingVisibility")
fun bindLoadingStatus(progress: ProgressBar, status: Status?) {
    when(status) {
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