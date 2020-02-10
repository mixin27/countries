package com.norm.countries.data.local

import androidx.room.TypeConverter
import timber.log.Timber

object CustomTypeConverters {

    @JvmStatic
    @TypeConverter
    fun fromStringArray(list: List<String>): String {
        val strBuilder = StringBuilder()
        if (list.isNotEmpty()) {
            list.forEach {
                if (list.lastIndex == list.size - 1) {
                    strBuilder.append(it)
                } else {
                    strBuilder.append(it).append("#")
                }
            }
        }

        Timber.d("FromStringArray = $strBuilder")
        return strBuilder.toString()
    }

    @JvmStatic
    @TypeConverter
    fun toStringArray(value: String): List<String> {
        return if (value.isEmpty()) {
            emptyList()
        } else {
            value.split("#")
        }
    }

//    @JvmStatic
//    @TypeConverter
//    fun fromDoubleArray(list: List<Double>): String {
//        val strBuilder = StringBuilder()
//        if (list.isNotEmpty()) {
//            list.forEach {
//                if (list.lastIndex == list.size - 1) {
//                    strBuilder.append(it)
//                } else {
//                    strBuilder.append(it).append("#")
//                }
//            }
//        }
//
//        Timber.d("FromStringArray = $strBuilder")
//        return strBuilder.toString()
//    }
//
//    @JvmStatic
//    @TypeConverter
//    fun toDoubleArray(value: String): List<Double> {
//        if (value.isEmpty()) {
//            return emptyList()
//        } else {
//            val list = value.split("#")
//            val result = mutableListOf<Double>()
//            list.forEach {
//                result.add(it.toDouble())
//            }
//            return result
//        }
//    }
}