package com.intechbel.techtask.feature.users.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("results")
    val results: List<UserApiModel>,
    @SerialName("info")
    val info: UserInfo? = null,
)

@Serializable
data class UserInfo(
    @SerialName("seed")
    val seed: String? = null,
    @SerialName("results")
    val results: Int? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("version")
    val version: String? = null,
)

