package com.intechbel.techtask.feature.root.presentation.core

import com.intechbel.techtask.feature.users.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
sealed interface RootConfiguration {
    @Serializable
    data object Users : RootConfiguration

    @Serializable
    data class UserDetails(val user: User) : RootConfiguration
}

