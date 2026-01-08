package com.intechbel.techtask.feature.users.presentation.logic

import com.intechbel.techtask.feature.users.domain.paging.UserPagingSource
import com.intechbel.techtask.feature.users.presentation.core.UserAction
import com.intechbel.techtask.feature.users.presentation.core.UserIntent
import com.intechbel.techtask.feature.users.presentation.core.UserLabel
import com.intechbel.techtask.feature.users.presentation.core.UserMessage
import com.intechbel.techtask.feature.users.presentation.core.UserState
import com.intechbel.techtask.pagination.EmptyPagingParam
import com.intechbel.techtask.presentation.components.base.BaseCoroutineExecutor

class UserExecutor(
    private val pagingSource: UserPagingSource,
) : BaseCoroutineExecutor<UserIntent, UserAction, UserState, UserMessage, UserLabel>() {

    override fun executeIntent(intent: UserIntent) {
        when (intent) {
            UserIntent.OnUserClick -> {
                publish(UserLabel.OnNavigateToUserDetails)
            }
        }
    }

    override fun executeAction(action: UserAction) {
        when (action) {
            is UserAction.OnFetchUsers -> {
                loadUsers()
            }
        }
    }

    private fun loadUsers() {
        scope.launchSingle(USERS_KEY) {
            pagingSource(EmptyPagingParam, scope)
        }
    }


    companion object {
        private const val USERS_KEY = "users_key"
    }
}

