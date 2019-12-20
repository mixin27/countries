package com.norm.countries.data.executor

import androidx.annotation.NonNull
import com.norm.countries.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
class JobExecutor internal constructor(): ThreadExecutor{

    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        this.threadPoolExecutor = ThreadPoolExecutor(
            POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            LinkedBlockingQueue(),
            JobThreadFactory()
        )
    }

    override fun execute(command: Runnable) {
        this.threadPoolExecutor.execute(command)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0
        override fun newThread(@NonNull runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }

    companion object {
        const val POOL_SIZE = 3
        const val MAX_POOL_SIZE = 5
        const val KEEP_ALIVE_TIME = 10L
    }
}