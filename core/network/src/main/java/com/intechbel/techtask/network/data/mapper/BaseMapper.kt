package com.intechbel.techtask.network.data.mapper

import com.intechbel.techtask.shared.ApiResult

abstract class BaseMapper<FROM, TO> {

    abstract fun mapData(from: FROM): TO

    fun map(from: ApiResult<FROM>): ApiResult<TO> = mapApiResult(from)

    private fun mapApiResult(from: ApiResult<FROM>): ApiResult<TO> = when (from) {
        is ApiResult.Error -> ApiResult.Error(from.message, from.code, from.messageBody, from.type)
        is ApiResult.Success -> ApiResult.Success(mapData(from.data))
        is ApiResult.SuccessNoResponse -> ApiResult.SuccessNoResponse()
    }
}

