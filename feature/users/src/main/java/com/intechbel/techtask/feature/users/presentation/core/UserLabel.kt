package com.intechbel.techtask.feature.users.presentation.core

import kotlinx.serialization.Serializable

@Serializable
sealed interface UserLabel {
    @Serializable
    data object None : UserLabel
}

