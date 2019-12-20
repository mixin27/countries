package com.norm.countries.data.exception

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
enum class Issue(
    private val shouldRetry: Boolean
) {
    NETWORK(true),
    SERVER(false),
    API(false);

    fun shouldRetry(): Boolean {
        return shouldRetry
    }
}