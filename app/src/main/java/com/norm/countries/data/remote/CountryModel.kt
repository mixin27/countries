package com.norm.countries.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Create by Kyaw Zayar Tun on 25/01/2020.
 */
@Parcelize
data class CountryModel(
    val numericCode: String,
    val name: String,
    val alpha2Code: String,
    val alpha3Code: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val population: Long,
    val demonym: String,
    val area: Double,
    val gini: Double,
    val nativeName: String,
    val flag: String?,
    val cioc: String
) : Parcelable {
    val getPopulation: String
        get() = population.toString()
}