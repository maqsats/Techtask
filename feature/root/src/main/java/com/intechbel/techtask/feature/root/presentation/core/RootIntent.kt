package com.intechbel.techtask.feature.root.presentation.core

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootIntent {
    @Serializable
    data object None : RootIntent
}

