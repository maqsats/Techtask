package com.intechbel.techtask.feature.users.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: UserName,
    val email: String,
    val picture: String,
    val phone: String?,
    val location: UserLocation,
)

@Serializable
data class UserName(
    val title: String,
    val first: String,
    val last: String,
)

@Serializable
data class UserLocation(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val coordinates: UserCoordinates,
) {
    val formattedAddress: String
        get() = listOfNotNull(street, city, state, country)
            .filter { it.isNotBlank() }
            .joinToString(separator = ", ")
}

@Serializable
data class UserCoordinates(
    val latitude: Double,
    val longitude: Double,
)
