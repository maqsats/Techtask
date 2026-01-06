package com.intechbel.techtask.shared

import com.intechbel.techtask.shared.common_models.ErrorType

sealed interface ContentUiState<out T> {
    data class Success<T>(val data: T) : ContentUiState<T>
    data object SuccessNoResponse : ContentUiState<Nothing>
    data class Error(
        val error: UiText,
        val type: ErrorType? = null,
    ) : ContentUiState<Nothing>

    data class Loading<T>(val data: T? = null) : ContentUiState<T>

    data object Idle : ContentUiState<Nothing>
}

