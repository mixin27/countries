package com.norm.countries.domain.usecase

import com.norm.countries.domain.executor.PostExecutionThread
import com.norm.countries.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
abstract class CompletableUseCase<Action> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
){

    fun execute(action: Action): Completable {
        return executeInternal(action)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }

    protected abstract fun executeInternal(action: Action): Completable
}