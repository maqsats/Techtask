package com.intechbel.techtask.feature.users.presentation.core

import kotlinx.serialization.Serializable

@Serializable
sealed interface UserIntent {
    @Serializable
    data object None : UserIntent
}

