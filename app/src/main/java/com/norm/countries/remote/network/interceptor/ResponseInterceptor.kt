package com.norm.countries.remote.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
class ResponseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        when (response.code) {
            404 -> HttpErrorEvent.emit(HttpErrorEvent.Event("Server not found", HttpErrorEvent.Type.SERVER_ERROR))
            401 -> HttpErrorEvent.emit(HttpErrorEvent.Event("Unauthorized", HttpErrorEvent.Type.UNAUTHORIZED))
            500 -> HttpErrorEvent.emit(HttpErrorEvent.Event("Server broken", HttpErrorEvent.Type.SERVER_ERROR))
        }
        return response
    }
}