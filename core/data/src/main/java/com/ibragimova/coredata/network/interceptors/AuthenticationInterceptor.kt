package com.ibragimova.coredata.network.interceptors

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

private const val AUTHORIZATION_HEADER = "api_key"
private const val PREFIX = "Bearer"

class AuthenticationInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val customRequest = chain.request().newBuilder()
            .method(originalRequest.method, originalRequest.body)
            .header(AUTHORIZATION_HEADER, PREFIX)
            .build()

        return chain.proceed(customRequest)
    }
}