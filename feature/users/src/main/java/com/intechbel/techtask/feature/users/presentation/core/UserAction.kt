package com.intechbel.techtask.feature.users.presentation.core

sealed interface UserAction {
    data object OnFetchUsers : UserAction
}
