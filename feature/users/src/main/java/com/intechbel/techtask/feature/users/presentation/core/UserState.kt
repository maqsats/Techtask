package com.intechbel.techtask.feature.users.presentation.core

import kotlinx.serialization.Serializable

@Serializable
data class UserState(
    val isLoading: Boolean = false,
) {
    companion object {
        fun initial() = UserState()
    }
}

