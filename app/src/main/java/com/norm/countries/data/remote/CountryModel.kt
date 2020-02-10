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
    val cioc: String,
    val topLevelDomain: List<String>,
    val callingCodes: List<String>,
    val altSpellings: List<String>,
//    val latlng: List<Double>,
    val timezones: List<String>,
    val borders: List<String>
) : Parcelable {
    val getPopulation: String
        get() = population.toString()

    val getText: String
        get() = "$capital | $nativeName | ${if (callingCodes.isNotEmpty()) callingCodes[0] else ""}"
}

@Parcelize
data class Currency(
    val code: String,
    val name: String,
    val symbol: String
) : Parcelable

@Parcelize
data class Language(
    val iso639_1: String,
    val iso639_2: String,
    val nativeName: String
) : Parcelable

@Parcelize
data class Translation(
    val de: String,
    val fr: String,
    val ja: String,
    val it: String,
    val br: String,
    val pt: String,
    val nl: String,
    val hr: String,
    val fa: String
) : Parcelable

@Parcelize
data class RegionalBloc(
    val acronym: String,
    val name: String,
    val otherAcronyms: List<String>,
    val otherNames: List<String>
) : Parcelable