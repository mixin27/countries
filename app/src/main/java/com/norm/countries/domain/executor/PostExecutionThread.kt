package com.norm.countries.domain.executor

import io.reactivex.Scheduler

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
interface PostExecutionThread {
    val scheduler : Scheduler
}