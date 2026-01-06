package com.intechbel.techtask.pagination

import com.intechbel.techtask.shared.ApiResult
import com.intechbel.techtask.shared.common_models.PagingParam

interface CustomPagingUseCase<T : Any, B : Any, Param : PagingParam> {
    suspend operator fun invoke(
        pageNumber: Int,
        pageSize: Int,
        pagingParam: Param,
    ): ApiResult<CustomPayload<T, B>>

    fun getPageSize(payload: CustomPayload<T, B>): Int = payload.list.size
}
