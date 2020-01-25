package com.norm.countries.network

/**
 * A generic class that holds a value with its loading status.
 * from [https://github.com/android/architecture-components-samples/blob/8874799313/GithubBrowserSample/app/src/main/java/com/android/example/github/vo/Resource.kt]
 * @param T
*/
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}