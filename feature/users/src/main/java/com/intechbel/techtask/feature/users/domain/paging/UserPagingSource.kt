package com.intechbel.techtask.feature.users.domain.paging

import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.feature.users.domain.usecase.UserPagingUseCase
import com.intechbel.techtask.pagination.BasePagingSource
import com.intechbel.techtask.pagination.EmptyPagingParam
import com.intechbel.techtask.pagination.PagingUseCase

class UserPagingSource(
    private val pagingUseCase: UserPagingUseCase,
) : BasePagingSource<User, EmptyPagingParam>() {

    override fun pagingUseCase(): PagingUseCase<User, EmptyPagingParam> = pagingUseCase

    override fun newInstance(): BasePagingSource<User, EmptyPagingParam> {
        return UserPagingSource(pagingUseCase)
    }
}

