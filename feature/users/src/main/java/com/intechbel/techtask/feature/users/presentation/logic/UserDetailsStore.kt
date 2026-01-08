package com.intechbel.techtask.feature.users.presentation.logic

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.feature.users.presentation.core.UserDetailsIntent
import com.intechbel.techtask.feature.users.presentation.core.UserDetailsLabel
import com.intechbel.techtask.feature.users.presentation.core.UserDetailsState

class UserDetailsStore(
    storeFactory: StoreFactory,
    user: User,
) : Store<UserDetailsIntent, UserDetailsState, UserDetailsLabel> by storeFactory.create(
    name = UserDetailsStore::class.simpleName,
    initialState = UserDetailsState.initial(user),
    executorFactory = {
        UserDetailsExecutor()
    },
    reducer = { message ->
        when (message) {
            else -> this
        }
    },
)

