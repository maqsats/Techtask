package com.intechbel.techtask.feature.users.data.api

import com.intechbel.techtask.feature.users.data.model.UserResponse
import com.intechbel.techtask.shared.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {
    @GET("api/")
    fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int = 50,
        @Query("inc") includeFields: String = "name,email,picture",
        @Query("seed") seed: String = "techtask",
        @Query("noinfo") noInfo: Boolean = true,
    ): ApiResult<UserResponse>
}

