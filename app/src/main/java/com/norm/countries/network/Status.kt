package com.norm.countries.network

/**
 * Status of a resource that is provided to the UI.
 *
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
 *
 * from [https://github.com/android/architecture-components-samples/blob/8874799313/GithubBrowserSample/app/src/main/java/com/android/example/github/vo/Status.kt]
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}