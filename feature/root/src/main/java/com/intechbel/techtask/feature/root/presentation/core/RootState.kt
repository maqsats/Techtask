package com.intechbel.techtask.feature.root.presentation.core

import kotlinx.serialization.Serializable

@Serializable
data class RootState(
    val isLoading: Boolean = false
) {
    companion object {
        fun initial() = RootState()
    }
}

