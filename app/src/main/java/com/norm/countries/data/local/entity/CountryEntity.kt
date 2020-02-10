package com.norm.countries.data.local.entity

import androidx.room.*
import com.norm.countries.data.remote.CountryModel

/**
 * Create by Kyaw Zayar Tun on 25/01/2020.
 */
@Entity(tableName = "countries")
data class CountryEntity(
    @ColumnInfo(name = "numeric_code")
    val numericCode: String,

    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "alpha2Code")
    val alpha2Code: String,

    @ColumnInfo(name = "alpha3Code")
    val alpha3Code: String,

    @ColumnInfo(name = "capital")
    val capital: String,

    @ColumnInfo(name = "region")
    val region: String,

    @ColumnInfo(name = "subregion")
    val subregion: String,

    @ColumnInfo(name = "population")
    val population: Long,

    @ColumnInfo(name = "demonym")
    val demonym: String,

    @ColumnInfo(name = "area")
    val area: Double,

    @ColumnInfo(name = "gini")
    val gini: Double,

    @ColumnInfo(name = "native_name")
    val nativeName: String,

    @ColumnInfo(name = "flag")
    val flag: String,

    @ColumnInfo(name = "cioc")
    val cioc: String,

    @ColumnInfo(name = "top_level_domain")
    val topLevelDomain: List<String>,

    @ColumnInfo(name = "calling_codes")
    val callingCodes: List<String>,

    @ColumnInfo(name = "alt_spellings")
    val altSpellings: List<String>,

//    @ColumnInfo(name = "lat_lng")
//    val latlng: List<Double>,

    @ColumnInfo(name = "timezones")
    val timezones: List<String>,

    @ColumnInfo(name = "borders")
    val borders: List<String>
)

@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey
    val code: String,
    val name: String,
    val symbol: String
)

fun List<Currency>.toRemoteCurrency(): List<com.norm.countries.data.remote.Currency> {
    return map {
        com.norm.countries.data.remote.Currency(
            code = it.code,
            name = it.name,
            symbol = it.symbol
        )
    }
}


@Entity(tableName = "language")
data class Language(
    @PrimaryKey
    val iso639_1: String,
    val iso639_2: String,
    val nativeName: String
)

fun List<Language>.toRemoteLanguage(): List<com.norm.countries.data.remote.Language> {
    return map {
        com.norm.countries.data.remote.Language(
            iso639_1 = it.iso639_1,
            iso639_2 = it.iso639_2,
            nativeName = it.nativeName
        )
    }
}

@Entity(tableName = "translation")
data class Translation(
    @PrimaryKey
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

fun List<Translation>.toRemoteTranslation(): List<com.norm.countries.data.remote.Translation> {
    return map {
        com.norm.countries.data.remote.Translation(
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

@Entity(tableName = "regional_bloc")
data class RegionalBloc(
    @PrimaryKey
    val acronym: String,
    val name: String,
    val otherAcronyms: List<String>,
    val otherNames: List<String>
)

fun List<RegionalBloc>.toRemoteRegionalBloc(): List<com.norm.countries.data.remote.RegionalBloc> {
    return map {
        com.norm.countries.data.remote.RegionalBloc(
            acronym = it.acronym,
            name = it.name,
            otherAcronyms = it.otherAcronyms,
            otherNames = it.otherNames
        )
    }
}

fun List<CountryEntity>.asDomainModel(): List<CountryModel> {
    return map {
        CountryModel(
            numericCode = it.numericCode,
            name = it.name,
            alpha2Code = it.alpha2Code,
            alpha3Code = it.alpha3Code,
            area = it.area,
            capital = it.capital,
            cioc = it.cioc,
            demonym = it.demonym,
            flag = it.flag,
            gini = it.gini,
            nativeName = it.nativeName,
            population = it.population,
            region = it.region,
            subregion = it.subregion,
            topLevelDomain = it.topLevelDomain,
            callingCodes = it.callingCodes,
            altSpellings = it.altSpellings,
//            latlng = it.latlng,
            timezones = it.timezones,
            borders = it.borders
        )
    }
}

//fun CountryEntity.asDomainModel(): CountryModel {
//    return CountryModel(
//        numericCode = this.numericCode,
//        name = this.name,
//        alpha2Code = this.alpha2Code,
//        alpha3Code = this.alpha3Code,
//        area = this.area,
//        capital = this.capital,
//        cioc = this.cioc,
//        demonym = this.demonym,
//        flag = this.flag,
//        gini = this.gini,
//        nativeName = this.nativeName,
//        population = this.population,
//        region = this.region,
//        subregion = this.subregion
//    )
//}