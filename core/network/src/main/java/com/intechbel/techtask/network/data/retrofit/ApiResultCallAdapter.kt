package com.intechbel.techtask.network.data.retrofit

import com.intechbel.techtask.shared.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import java.lang.reflect.Type

internal class ApiResultCallAdapter<T>(
    private val successType: Type,
) : CallAdapter<T, Call<ApiResult<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<ApiResult<T>> = ApiResultCall(call)
}

internal class ApiResultBodyCallAdapter<T>(
    private val successType: Type,
) : CallAdapter<T, ApiResult<T>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): ApiResult<T> {
        return try {
            val response: Response<T> = call.execute()
            when (response.code()) {
                in 200..208 -> response.body()?.let { ApiResult.Success(it) }
                    ?: ApiResult.SuccessNoResponse()

                else -> response.convertToApiResult()
            }
        } catch (throwable: Throwable) {
            throwable.catchError()
        }
    }
}

