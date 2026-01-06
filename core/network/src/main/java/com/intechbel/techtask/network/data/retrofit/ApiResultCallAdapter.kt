package com.intechbel.techtask.network.data.retrofit

import com.intechbel.techtask.shared.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ApiResultCallAdapter<T>(
    private val successType: Type,
) : CallAdapter<T, Call<ApiResult<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<ApiResult<T>> = ApiResultCall(call)
}

