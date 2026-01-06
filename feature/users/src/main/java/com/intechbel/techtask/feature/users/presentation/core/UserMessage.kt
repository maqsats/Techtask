package com.intechbel.techtask.feature.users.presentation.core

import kotlinx.serialization.Serializable

@Serializable
sealed interface UserMessage {
    @Serializable
    data object None : UserMessage
}

