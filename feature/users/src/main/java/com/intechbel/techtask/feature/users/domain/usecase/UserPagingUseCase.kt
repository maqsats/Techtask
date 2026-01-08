package com.intechbel.techtask.feature.users.domain.usecase

import com.intechbel.techtask.feature.users.data.repository.UserRepository
import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.pagination.EmptyPagingParam
import com.intechbel.techtask.pagination.PagingUseCase
import com.intechbel.techtask.shared.ApiResult

class UserPagingUseCase(
    private val repository: UserRepository,
) : PagingUseCase<User, EmptyPagingParam> {
    override suspend fun invoke(
        pageNumber: Int,
        pageSize: Int,
        pagingParam: EmptyPagingParam,
    ): ApiResult<List<User>> {
        // API uses 1-based page numbers, but our paging uses 0-based
        // So we add 1 to convert 0-based to 1-based
        return repository.getUsers(page = pageNumber + 1, pageSize = pageSize)
    }
}