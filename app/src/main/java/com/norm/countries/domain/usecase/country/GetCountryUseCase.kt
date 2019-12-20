package com.norm.countries.domain.usecase.country

import com.norm.countries.domain.executor.PostExecutionThread
import com.norm.countries.domain.executor.ThreadExecutor
import com.norm.countries.domain.model.Country
import com.norm.countries.domain.repository.CountryRepository
import com.norm.countries.domain.usecase.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
class GetCountryUseCase
@Inject constructor(
    private val countryRepository: CountryRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<GetCountryUseCase.Action, List<Country>>(threadExecutor, postExecutionThread) {

    class Action {}

    override fun execute(action: Action): Observable<List<Country>> =
        countryRepository.getAllCountries().toObservable()

    override fun progress(): List<Country> = listOf(Country.progress())

    override fun error(throwable: Throwable): List<Country> = listOf(Country.error(throwable))
}