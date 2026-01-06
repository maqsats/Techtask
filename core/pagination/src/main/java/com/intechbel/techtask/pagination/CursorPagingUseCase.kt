package com.intechbel.techtask.pagination

import com.intechbel.techtask.shared.ApiResult
import com.intechbel.techtask.shared.common_models.PagingParam

interface CursorPagingUseCase<T : Any, Param : PagingParam> {
    suspend operator fun invoke(key: Int?, pagingParam: Param): ApiResult<List<T>>
}

