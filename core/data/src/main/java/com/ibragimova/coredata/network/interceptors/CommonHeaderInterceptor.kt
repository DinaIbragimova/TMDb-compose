package com.ibragimova.coredata.network.interceptors

import com.ibragimova.corecommon.model.generateRequestId
import com.ibragimova.corecommon.strings.DEFAULT_LOCALE
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CommonHeaderInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val customRequest = chain.request().newBuilder()
            .method(originalRequest.method, originalRequest.body)
            .header("X-Request-ID", generateRequestId())
            .header("Content-Type", "application/json;charset=utf-8")
            .header("Accept", "*/*")
            .header("Accept-Language", DEFAULT_LOCALE.language)
            .build()

        chain.request()

        return chain.proceed(customRequest)
    }
}