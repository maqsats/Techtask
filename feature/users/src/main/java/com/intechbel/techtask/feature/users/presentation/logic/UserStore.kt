package com.intechbel.techtask.feature.users.presentation.logic

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.intechbel.techtask.feature.users.domain.paging.UserPagingSource
import com.intechbel.techtask.feature.users.presentation.core.UserAction
import com.intechbel.techtask.feature.users.presentation.core.UserIntent
import com.intechbel.techtask.feature.users.presentation.core.UserLabel
import com.intechbel.techtask.feature.users.presentation.core.UserState

class UserStore(
    storeFactory: StoreFactory,
    pagingSource: UserPagingSource,
) : Store<UserIntent, UserState, UserLabel> by storeFactory.create(
    name = UserStore::class.simpleName,
    initialState = UserState.initial(),
    bootstrapper = coroutineBootstrapper {
        dispatch(UserAction.OnFetchUsers)
    },
    executorFactory = {
        UserExecutor(pagingSource)
    },
    reducer = { message ->
        when (message) {
            else -> this
        }
    },
)

