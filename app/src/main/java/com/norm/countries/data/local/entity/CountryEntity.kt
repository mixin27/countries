package com.norm.countries.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val cioc: String
)

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
            subregion = it.subregion
        )
    }
}