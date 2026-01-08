package com.intechbel.techtask.network

import android.annotation.SuppressLint
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.intechbel.techtask.logger.Logger
import com.intechbel.techtask.network.data.interceptors.TimeOutInterceptor
import com.intechbel.techtask.network.data.retrofit.ApiResultAdapterFactory
import com.intechbel.techtask.network.data.retrofit.CustomConverterFactory
import com.intechbel.techtask.network.domain.NetworkConstants
import com.intechbel.techtask.network.util.ConnectivityManagerNetworkMonitor
import com.intechbel.techtask.network.util.NetworkMonitor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@OptIn(ExperimentalSerializationApi::class)
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
        ChuckerInterceptor.Builder(this.androidApplication())
            .collector(
                ChuckerCollector(
                    context = this.androidApplication(),
                    showNotification = true,
                )
            )
            .build()
    }

    single {
        SecureRandom()
    }

    single {
        val trustAllCerts: Array<TrustManager> = arrayOf(
            @SuppressLint("CustomX509TrustManager")
            object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?,
                ) {
                    // Do nothing
                }

                @SuppressLint("TrustAllX509TrustManager")
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
        val sslContext: SSLContext = SSLContext.getInstance(NetworkConstants.TRUST_SSL)
        sslContext.init(null, get<Array<TrustManager>>(), get<SecureRandom>())
        sslContext
    }

    single {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            isLenient = true
            encodeDefaults = true
        }
    }

    single {
        val sslContext = get<SSLContext>()
        val trustManager = get<Array<TrustManager>>()[0] as X509TrustManager

        OkHttpClient.Builder()
            .addInterceptor(get<TimeOutInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<ChuckerInterceptor>())
            .connectTimeout(NetworkConstants.TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NetworkConstants.TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .sslSocketFactory(sslContext.socketFactory, trustManager)
            .hostnameVerifier { _, _ -> true } // WARNING: Only for development, remove in production
            .build()
    }

    single {
        val jsonConverter =
            get<Json>().asConverterFactory(NetworkConstants.APPLICATION_JSON.toMediaType())
        Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(CustomConverterFactory(jsonConverter))
            .addCallAdapterFactory(ApiResultAdapterFactory())
            .build()
    }

    single { ConnectivityManagerNetworkMonitor(get()) }
    factory<NetworkMonitor> { ConnectivityManagerNetworkMonitor(get()) }
}
