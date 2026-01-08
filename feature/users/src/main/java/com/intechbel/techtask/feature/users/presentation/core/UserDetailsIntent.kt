package com.intechbel.techtask.feature.users.presentation.core

import kotlinx.serialization.Serializable

@Serializable
sealed interface UserDetailsIntent {
    @Serializable
    data object OnBackClick : UserDetailsIntent
}

