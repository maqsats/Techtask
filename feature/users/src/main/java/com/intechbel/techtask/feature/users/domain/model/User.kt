package com.intechbel.techtask.feature.users.domain.model

data class User(
    val name: UserName,
    val email: String,
    val picture: String,
)

data class UserName(
    val title: String,
    val first: String,
    val last: String,
)
