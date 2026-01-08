package com.intechbel.techtask.feature.users.presentation.core

import com.intechbel.techtask.feature.users.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsState(
    val user: User,
) {
    companion object {
        fun initial(user: User) = UserDetailsState(user = user)
    }
}

