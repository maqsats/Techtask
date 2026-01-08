package com.intechbel.techtask.feature.users.presentation.core

import com.intechbel.techtask.feature.users.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
sealed interface UserIntent {
    @Serializable
    data class OnUserClick(val user: User) : UserIntent
}

