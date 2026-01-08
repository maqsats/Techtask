package com.intechbel.techtask.feature.users.presentation.logic

import com.intechbel.techtask.feature.users.presentation.core.UserDetailsIntent
import com.intechbel.techtask.feature.users.presentation.core.UserDetailsLabel
import com.intechbel.techtask.feature.users.presentation.core.UserDetailsState
import com.intechbel.techtask.presentation.components.NoAction
import com.intechbel.techtask.presentation.components.NoMessage
import com.intechbel.techtask.presentation.components.base.BaseCoroutineExecutor

class UserDetailsExecutor :
    BaseCoroutineExecutor<UserDetailsIntent, NoAction, UserDetailsState, NoMessage, UserDetailsLabel>() {

    override fun executeIntent(intent: UserDetailsIntent) {
        when (intent) {
            UserDetailsIntent.OnBackClick -> publish(UserDetailsLabel.OnNavigateBack)
        }
    }

    override fun executeAction(action: NoAction) {
        // No-op - there are no actions for user details yet
    }
}

