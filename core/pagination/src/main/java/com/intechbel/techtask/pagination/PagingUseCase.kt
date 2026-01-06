package com.intechbel.techtask.pagination

import com.intechbel.techtask.shared.ApiResult
import com.intechbel.techtask.shared.common_models.PagingParam

interface PagingUseCase<T : Any, Param : PagingParam> {
    suspend operator fun invoke(
        pageNumber: Int,
        pageSize: Int,
        pagingParam: Param
    ): ApiResult<List<T>>

    fun getPageSize(list: List<T>, param: Param): Int = list.size
}
