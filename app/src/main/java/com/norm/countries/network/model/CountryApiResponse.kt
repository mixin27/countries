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
    val topLevelDomain: List<String>,
    val alpha2Code: String? = "",
    val alpha3Code: String? = "",
    val callingCodes: List<String>,
    val capital: String? = "",
    val altSpellings: List<String>,
    val region: String? = "",
    val subregion: String? = "",
    val population: Long? = 0L,
    val latlng: List<Double>,
    val demonym: String? = "",
    val area: Double? = 0.0,
    val gini: Double? = 0.0,
    val timezones: List<String>,
    val borders: List<String>,
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

fun List<Currency>.toDataCurrency(): List<com.norm.countries.data.local.entity.Currency> {
    return map {
        com.norm.countries.data.local.entity.Currency(
            code = it.code,
            name = it.name,
            symbol = it.symbol
        )
    }
}

data class Language(
    val iso639_1: String,
    val iso639_2: String,
    val nativeName: String
)

fun List<Language>.toDataLanguage(): List<com.norm.countries.data.local.entity.Language> {
    return map {
        com.norm.countries.data.local.entity.Language(
            iso639_1 = it.iso639_1,
            iso639_2 = it.iso639_2,
            nativeName = it.nativeName
        )
    }
}

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

fun List<Translation>.toDataTranslation(): List<com.norm.countries.data.local.entity.Translation> {
    return map {
        com.norm.countries.data.local.entity.Translation(
            de = it.de,
            fr = it.fr,
            ja = it.ja,
            it = it.it,
            br = it.br,
            pt = it.pt,
            nl = it.nl,
            hr = it.hr,
            fa = it.fa
        )
    }
}

data class RegionalBloc(
    val acronym: String,
    val name: String,
    val otherAcronyms: ArrayList<String>,
    val otherNames: ArrayList<String>
)

fun List<RegionalBloc>.toDataRegionalBloc(): List<com.norm.countries.data.local.entity.RegionalBloc> {
    return map {
        com.norm.countries.data.local.entity.RegionalBloc(
            acronym = it.acronym,
            name = it.name,
            otherAcronyms = it.otherAcronyms,
            otherNames = it.otherNames
        )
    }
}

fun List<Country>.asDatabaseModel(): List<CountryEntity> {
    return map {
        CountryEntity(
            numericCode = it.numericCode ?: "",
            name = it.name!!,
            alpha2Code = it.alpha2Code ?: "",
            alpha3Code = it.alpha3Code ?: "",
            area = it.area ?: 0.0,
            capital = it.capital ?: "",
            cioc = it.cioc ?: "",
            demonym = it.demonym ?: "",
            flag = it.flag ?: "",
            gini = it.gini ?: 0.0,
            nativeName = it.nativeName ?: "",
            population = it.population ?: 0L,
            region = it.region ?: "",
            subregion = it.subregion ?: "",
            topLevelDomain = it.topLevelDomain,
            callingCodes = it.callingCodes,
            altSpellings = it.altSpellings,
//            latlng = it.latlng,
            timezones = it.timezones,
            borders = it.borders
        )
    }
}