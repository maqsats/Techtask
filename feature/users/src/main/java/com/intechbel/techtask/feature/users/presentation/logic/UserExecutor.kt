package com.intechbel.techtask.feature.users.presentation.logic

import com.intechbel.techtask.feature.users.presentation.core.UserIntent
import com.intechbel.techtask.feature.users.presentation.core.UserLabel
import com.intechbel.techtask.feature.users.presentation.core.UserMessage
import com.intechbel.techtask.feature.users.presentation.core.UserState
import com.intechbel.techtask.presentation.components.NoAction
import com.intechbel.techtask.presentation.components.base.BaseCoroutineExecutor

class UserExecutor :
    BaseCoroutineExecutor<UserIntent, NoAction, UserState, UserMessage, UserLabel>() {
    override fun executeIntent(intent: UserIntent) {
        when (intent) {
            // Handle intents here when implemented
            UserIntent.None -> {
                // No-op
            }
        }
    }

    override fun executeAction(action: NoAction) {
        // Handle actions here when implemented
    }
}

