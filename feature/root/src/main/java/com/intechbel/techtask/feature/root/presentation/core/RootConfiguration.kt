package com.intechbel.techtask.feature.root.presentation.core

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootConfiguration {
    @Serializable
    data object Users : RootConfiguration
}

