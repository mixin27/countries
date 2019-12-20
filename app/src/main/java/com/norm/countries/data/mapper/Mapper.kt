package com.norm.countries.data.mapper

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
abstract class Mapper<D, E> {
    abstract fun map(entity: E): D
}