package com.intechbel.techtask.feature.users.presentation.logic

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.intechbel.techtask.feature.users.presentation.core.UserIntent
import com.intechbel.techtask.feature.users.presentation.core.UserLabel
import com.intechbel.techtask.feature.users.presentation.core.UserState

class UserStore(
    storeFactory: StoreFactory,
) : Store<UserIntent, UserState, UserLabel> by storeFactory.create(
    name = UserStore::class.simpleName,
    initialState = UserState.initial(),
    executorFactory = {
        UserExecutor()
    },
    reducer = { message ->
        when (message) {
            // Handle messages here when implemented
            else -> this
        }
    },
)

