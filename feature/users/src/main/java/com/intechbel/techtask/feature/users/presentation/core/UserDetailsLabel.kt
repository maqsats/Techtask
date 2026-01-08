package com.intechbel.techtask.feature.users.presentation.core

import kotlinx.serialization.Serializable

@Serializable
sealed interface UserDetailsLabel {
    @Serializable
    data object OnNavigateBack : UserDetailsLabel
}

