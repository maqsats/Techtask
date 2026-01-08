package com.intechbel.techtask.network.data.retrofit

import com.intechbel.techtask.shared.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResultAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // Handle direct ApiResult<T> return types (non-Call)
        if (getRawType(returnType) == ApiResult::class.java) {
            check(returnType is ParameterizedType)
            val successType = getParameterUpperBound(0, returnType)
            return ApiResultBodyCallAdapter<Any>(successType)
        }

        // Handle Call<ApiResult<T>> return types
        if (Call::class.java != getRawType(returnType)) return null
        check(returnType is ParameterizedType)

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != ApiResult::class.java) return null
        check(responseType is ParameterizedType)

        val successType = getParameterUpperBound(0, responseType)

        return ApiResultCallAdapter<Any>(successType)
    }
}

