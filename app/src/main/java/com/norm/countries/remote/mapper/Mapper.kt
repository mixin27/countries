package com.norm.countries.remote.mapper

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
interface Mapper<R, E> {
    fun map(response: R): E
}