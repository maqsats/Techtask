package com.intechbel.techtask.feature.users.data.repository

import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.shared.ApiResult

interface UserRepository {
    suspend fun getUsers(page: Int, pageSize: Int): ApiResult<List<User>>
}

