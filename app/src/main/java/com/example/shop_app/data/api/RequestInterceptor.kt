package com.example.shop_app.data.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("Content-Type", "application/json")
            .header("Content-Length", "83")
            .header("Host", originalRequest.url().host())
            .build()
        return chain.proceed(modifiedRequest)

    }
}