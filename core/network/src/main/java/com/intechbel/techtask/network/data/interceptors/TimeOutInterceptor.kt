package com.intechbel.techtask.network.data.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

class TimeOutInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val r: Request = chain.request()

        var connectTimeout = DEFAULT_TIMEOUT
        var readTimeout = DEFAULT_TIMEOUT
        var writeTimeout = DEFAULT_TIMEOUT

        val connectNew: String? = r.header(CONNECT_TIMEOUT)
        val readNew: String? = r.header(READ_TIMEOUT)
        val writeNew: String? = r.header(WRITE_TIMEOUT)

        if (!connectNew.isNullOrEmpty()) {
            connectTimeout = Integer.valueOf(connectNew)
        }
        if (!readNew.isNullOrEmpty()) {
            readTimeout = Integer.valueOf(readNew)
        }
        if (!writeNew.isNullOrEmpty()) {
            writeTimeout = Integer.valueOf(writeNew)
        }
        return chain
            .withConnectTimeout(connectTimeout, TimeUnit.SECONDS)
            .withReadTimeout(readTimeout, TimeUnit.SECONDS)
            .withWriteTimeout(writeTimeout, TimeUnit.SECONDS)
            .proceed(r)
    }

    companion object {
        const val CONNECT_TIMEOUT = "CONNECT_TIMEOUT"
        const val READ_TIMEOUT = "READ_TIMEOUT"
        const val WRITE_TIMEOUT = "WRITE_TIMEOUT"
        const val DEFAULT_TIMEOUT = 30
    }
}

