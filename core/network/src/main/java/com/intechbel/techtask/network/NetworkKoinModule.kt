package com.intechbel.techtask.network

import android.annotation.SuppressLint
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.intechbel.techtask.logger.Logger
import com.intechbel.techtask.network.data.interceptors.TimeOutInterceptor
import com.intechbel.techtask.network.util.ConnectivityManagerNetworkMonitor
import com.intechbel.techtask.network.util.NetworkMonitor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

const val TRUST_SSL = "SSL"
val networkModules = module {
    singleOf(::TimeOutInterceptor)

    single {
        HttpLoggingInterceptor { message ->
            Logger.d("Http: $message")
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        ChuckerInterceptor.Builder(this.androidApplication()).build()
    }

    single {
        SecureRandom()
    }

    single {
        val trustAllCerts: Array<TrustManager> = arrayOf(
            @SuppressLint("CustomX509TrustManager")
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?,
                ) {
                    // Do nothing
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?,
                ) {
                    // Do nothing
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )
        trustAllCerts
    }
    single {
        SecureRandom()
    }
    single {
        val sslContext: SSLContext = SSLContext.getInstance(TRUST_SSL)
        sslContext.init(null, get<Array<TrustManager>>(), get<SecureRandom>())
        sslContext
    }
    factory {
        get<SSLContext>().socketFactory // Create an ssl socket factory with our all-trusting manager
    }
    single { ConnectivityManagerNetworkMonitor(get()) }
    factory<NetworkMonitor> { ConnectivityManagerNetworkMonitor(get()) }
}
