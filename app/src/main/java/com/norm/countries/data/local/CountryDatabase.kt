package com.norm.countries.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.norm.countries.data.local.dao.CountryDao
import com.norm.countries.data.local.entity.*

/**
 * Create by Kyaw Zayar Tun on 25/01/2020.
 */
@Database(
    entities = [CountryEntity::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(value = [CustomTypeConverters::class])
abstract class CountryDatabase : RoomDatabase() {
    abstract val countryDao: CountryDao

    companion object {
        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountryDatabase::class.java,
                        "country_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}