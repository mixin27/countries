package com.norm.countries.domain.model

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
data class Country(
    val name: String? = null,
    override val throwable: Throwable?,
    override val state: State
) : DomainModel {
    companion object {
        fun success(
            name: String? = null
        ) : Country = Country(
            name,
            null,
            State.SUCCESS
        )

        fun progress() : Country = Country(
            "",
            null,
            State.PROGRESS
        )

        fun error(throwable: Throwable?) : Country = Country(
            "",
            throwable,
            State.ERROR
        )
    }
}