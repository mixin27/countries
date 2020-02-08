package com.norm.countries.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.norm.countries.data.local.entity.CountryEntity

/**
 * Create by Kyaw Zayar Tun on 25/01/2020.
 */
@Dao
interface CountryDao {
    @Insert
    fun insert(country: CountryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( countries: List<CountryEntity>)

    @Update
    fun update(country: CountryEntity)

    @Query("SELECT * from countries WHERE name = :name")
    fun get(name: String): LiveData<CountryEntity?>

    @Query("DELETE FROM countries")
    fun clear()

    @Query("SELECT * FROM countries ORDER BY name DESC LIMIT 1")
    fun getCountry(): CountryEntity?

    @Query("SELECT * FROM countries ORDER BY name")
    fun getAllCountries(): LiveData<List<CountryEntity>>

    @Query("SELECT * FROM countries WHERE name LIKE '%' || :q || '%'")
    fun searchSource(q: String): LiveData<List<CountryEntity>>
}