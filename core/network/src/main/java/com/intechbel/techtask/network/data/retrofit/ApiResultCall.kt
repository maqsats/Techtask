package com.intechbel.techtask.network.data.retrofit

import android.accounts.NetworkErrorException
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import com.intechbel.techtask.logger.Logger
import com.intechbel.techtask.network.R
import com.intechbel.techtask.network.data.model.ErrorResponse
import com.intechbel.techtask.shared.ApiResult
import com.intechbel.techtask.shared.UiText
import com.intechbel.techtask.shared.common_models.ErrorType
import okhttp3.Request
import okio.IOException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Suppress("MagicNumber")
internal class ApiResultCall<T>(
    private val callDelegate: Call<T>,
) : Call<ApiResult<T>> {

    override fun enqueue(callback: Callback<ApiResult<T>>) = callDelegate.enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                when (response.code()) {
                    in 200..208 -> {
                        response.body()?.let {
                            callback.onResponse(
                                this@ApiResultCall,
                                Response.success(
                                    ApiResult.Success(
                                        it
                                    )
                                )
                            )
                        } ?: callback.onResponse(
                            this@ApiResultCall,
                            Response.success(
                                ApiResult.SuccessNoResponse()
                            )
                        )
                    }

                    in 400..409 -> {
                        callback.onResponse(
                            this@ApiResultCall,
                            Response.success(
                                response.convertToApiResult()
                            )
                        )
                    }

                    else -> {
                        callback.onResponse(
                            this@ApiResultCall,
                            Response.success(
                                ApiResult.Error(
                                    UiText.StringResource(R.string.service_unavailable_full),
                                    response.code(),
                                    response.errorBody()?.string()
                                )
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                callback.onResponse(
                    this@ApiResultCall,
                    Response.success(
                        throwable.catchError()
                    )
                )
                call.cancel()
            }
        },
    )

    override fun clone(): Call<ApiResult<T>> = ApiResultCall(callDelegate.clone())

    override fun execute(): Response<ApiResult<T>> =
        throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted(): Boolean = callDelegate.isExecuted

    override fun cancel() = callDelegate.cancel()

    override fun isCanceled(): Boolean = callDelegate.isCanceled

    override fun request(): Request = callDelegate.request()

    override fun timeout(): Timeout = callDelegate.timeout()
}

@OptIn(ExperimentalSerializationApi::class)
private fun <T, T1> Response<T>?.convertToApiResult(): ApiResult<T1> {
    val errorBodyString = this?.errorBody()?.string()
    return try {
        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
        val errorParsed = json.decodeFromString<ErrorResponse>(errorBodyString.orEmpty())
        ApiResult.Error(
            UiText.DynamicString(
                errorParsed.errorMessage
                    ?: errorParsed.errorDescription.orEmpty()
            ),
            errorParsed.errorCode ?: this?.code(),
            errorBodyString
        )
    } catch (e: Exception) {
        Logger.e(e, e.message + errorBodyString)
        ApiResult.Error(
            UiText.StringResource(R.string.service_unavailable_full),
            this?.code(),
            errorBodyString
        )
    }
}

private fun <T> Throwable.catchError(): ApiResult<T> {
    Logger.e(this, this.message)
    this.printStackTrace()
    return when (this) {
        is HttpException -> {
            this.response().convertToApiResult()
        }

        is UnknownHostException -> {
            ApiResult.Error(UiText.StringResource(R.string.no_internet_connection), type = ErrorType.NO_NETWORK)
        }

        is NetworkErrorException -> {
            ApiResult.Error(UiText.StringResource(R.string.no_internet_connection), type = ErrorType.NO_NETWORK)
        }

        is SocketTimeoutException -> {
            ApiResult.Error(UiText.StringResource(R.string.service_unavailable_full))
        }

        is IOException -> {
            ApiResult.Error(UiText.StringResource(R.string.service_unavailable_full))
        }

        is IllegalStateException -> {
            ApiResult.Error(UiText.StringResource(R.string.service_unavailable_full))
        }

        else -> {
            ApiResult.Error(UiText.StringResource(R.string.service_unavailable_full))
        }
    }
}

