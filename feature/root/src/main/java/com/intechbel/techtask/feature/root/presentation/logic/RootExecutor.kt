package com.intechbel.techtask.feature.root.presentation.logic

import com.intechbel.techtask.feature.root.presentation.core.RootIntent
import com.intechbel.techtask.feature.root.presentation.core.RootLabel
import com.intechbel.techtask.feature.root.presentation.core.RootMessage
import com.intechbel.techtask.feature.root.presentation.core.RootState
import com.intechbel.techtask.presentation.components.NoAction
import com.intechbel.techtask.presentation.components.base.BaseCoroutineExecutor

class RootExecutor :
    BaseCoroutineExecutor<RootIntent, NoAction, RootState, RootMessage, RootLabel>() {
    override fun executeIntent(intent: RootIntent) {
        when (intent) {
            // Handle intents here when implemented
            RootIntent.None -> {
                // No-op
            }
        }
    }

    override fun executeAction(action: NoAction) {
        // Handle actions here when implemented
    }
}

