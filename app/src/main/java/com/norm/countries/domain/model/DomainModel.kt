package com.norm.countries.domain.model

import androidx.annotation.Nullable

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
interface DomainModel {
    val throwable: Throwable?
    val state: State
}