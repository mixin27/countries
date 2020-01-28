package com.norm.countries.network.model

import com.norm.countries.data.local.entity.CountryEntity
import com.norm.countries.data.remote.CountryModel
import com.squareup.moshi.Json

/**
 * Create by Kyaw Zayar Tun on 28/01/2020.
 */
data class CountriesResponse(
    val countries: List<Country>
)

data class Country(
    val name: String?,
//    val topLevelDomain: ArrayList<String>,
    val alpha2Code: String? = "",
    val alpha3Code: String? = "",
//    val callingCodes: ArrayList<String>,
    val capital: String? = "",
//    val altSpellings: ArrayList<String>,
    val region: String? = "",
    val subregion: String? = "",
    val population: Long? = 0L,
//    val latlng: ArrayList<Int>,
    val demonym: String? = "",
    val area: Long? = 0L,
    val gini: Double? = 0.0,
//    val timezones: ArrayList<String>,
//    val borders: ArrayList<String>,
    val nativeName: String? = "",
    val numericCode: String? = "",
//    val currencies: List<Currency>,
//    val languages: List<Language>,
//    val translations: List<Translation>,
//    val regionalBlocs: List<RegionalBloc>,
    val flag: String? = "",
    val cioc: String? = ""
)

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)

data class Language(
    val iso639_1: String,
    val iso639_2: String,
    val nativeName: String
)

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
)

data class RegionalBloc(
    val acronym: String,
    val name: String,
    val otherAcronyms: ArrayList<String>,
    val otherNames: ArrayList<String>
)

fun List<Country>.asDatabaseModel(): List<CountryEntity> {
    return map {
        CountryEntity(
            numericCode = it.numericCode!!,
            name = it.name!!,
            alpha2Code = it.alpha2Code!!,
            alpha3Code = it.alpha3Code!!,
            area = it.area!!,
            capital = it.capital!!,
            cioc = it.cioc!!,
            demonym = it.demonym!!,
            flag = it.flag!!,
            gini = it.gini!!,
            nativeName = it.nativeName!!,
            population = it.population!!,
            region = it.region!!,
            subregion = it.subregion!!
        )
    }
}