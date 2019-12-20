package com.norm.countries.domain.usecase

import com.norm.countries.domain.executor.PostExecutionThread
import com.norm.countries.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
abstract class UseCase<Action, Result> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) : ObservableTransformer<Action, Result> {

    /**
     * Applies a function to the upstream Observable and returns an ObservableSource with
     * optionally different element type.
     * @param upstream the upstream Observable instance
     * @return the transformed ObservableSource instance
     */
    override fun apply(upstream: Observable<Action>): ObservableSource<Result> {
        return upstream.flatMap(this::execute)
            .onErrorReturn(this::error)
            .subscribeOn(Schedulers.from(threadExecutor))
            .startWith(progress())
            .observeOn(postExecutionThread.scheduler)
    }

    /**
     * Execute the action.
     * @param action Action that triggers the use case operation
     * @return A stream of Result model that represents the fact the operation has succeeded
     */
    abstract fun execute(action: Action): Observable<Result>

    /**
     * Action in progress.
     * @return Result model that represents the fact that the operation is in progress
     */
    abstract fun progress(): Result

    /**
     * Action in error occur.
     * @param throwable Exception that wraps the reason for failure and will be handled in {@code ModelMapper} of app module
     * @return Result model that represents the fact that the operation has failed
     */
    abstract fun error(throwable: Throwable): Result

}