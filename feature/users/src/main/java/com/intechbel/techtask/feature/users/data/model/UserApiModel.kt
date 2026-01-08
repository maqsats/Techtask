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
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("location")
    val location: UserLocationApiModel = UserLocationApiModel(),
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

@Serializable
data class UserLocationApiModel(
    @SerialName("street")
    val street: UserStreetApiModel? = null,
    @SerialName("city")
    val city: String = "",
    @SerialName("state")
    val state: String = "",
    @SerialName("country")
    val country: String = "",
    @SerialName("coordinates")
    val coordinates: UserCoordinatesApiModel? = null,
)

@Serializable
data class UserStreetApiModel(
    @SerialName("number")
    val number: Int? = null,
    @SerialName("name")
    val name: String = "",
)

@Serializable
data class UserCoordinatesApiModel(
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
)
