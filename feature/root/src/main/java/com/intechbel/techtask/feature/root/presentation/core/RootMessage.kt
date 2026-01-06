package com.intechbel.techtask.feature.root.presentation.core

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootMessage {
    @Serializable
    data object None : RootMessage
}

