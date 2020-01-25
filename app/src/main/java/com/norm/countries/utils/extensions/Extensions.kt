@file:JvmName("ExtensionUtils")

package com.norm.countries.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Create by Kyaw Zayar Tun on 25/01/2020.
 */
fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachedToBase: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachedToBase)
}