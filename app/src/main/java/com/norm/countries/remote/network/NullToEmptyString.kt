package com.norm.countries.remote.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import org.jetbrains.annotations.Nullable

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class NullToEmptyString

class NullToEmptyStringAdapter {
    fun toJson(@NullToEmptyString value: String?): String? {
        return value
    }

    @FromJson
    @NullToEmptyString
    fun fromJson(@Nullable data: String?): String? {
        return data ?: ""
    }
}