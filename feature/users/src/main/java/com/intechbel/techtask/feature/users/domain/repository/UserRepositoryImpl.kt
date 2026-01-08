package com.intechbel.techtask.feature.users.domain.repository

import com.intechbel.techtask.feature.users.data.api.UserApiService
import com.intechbel.techtask.feature.users.data.model.UserMapper
import com.intechbel.techtask.feature.users.data.repository.UserRepository
import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.shared.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val apiService: UserApiService,
    private val userMapper: UserMapper
) : UserRepository {
    override suspend fun getUsers(page: Int, pageSize: Int): ApiResult<List<User>> =
        withContext(Dispatchers.IO) {
            userMapper.map(apiService.getUsers(page = page, results = pageSize))
        }
}
