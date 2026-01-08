package com.intechbel.techtask.feature.users.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApiModel(
    @SerialName("name")
    val name: UserNameApiModel,
    @SerialName("email")
    val email: String,
    @SerialName("picture")
    val picture: UserPictureApiModel,
)

@Serializable
data class UserNameApiModel(
    @SerialName("title")
    val title: String, // Mr, Ms, Miss, Mrs
    @SerialName("first")
    val first: String,
    @SerialName("last")
    val last: String,
)

@Serializable
data class UserPictureApiModel(
    @SerialName("large")
    val large: String,
    @SerialName("medium")
    val medium: String,
    @SerialName("thumbnail")
    val thumbnail: String,
)
