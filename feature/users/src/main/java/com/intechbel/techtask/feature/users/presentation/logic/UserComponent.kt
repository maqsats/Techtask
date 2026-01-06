package com.intechbel.techtask.feature.users.presentation.logic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.intechbel.techtask.feature.users.presentation.core.IUserComponent
import com.intechbel.techtask.feature.users.presentation.core.UserIntent
import com.intechbel.techtask.feature.users.presentation.core.UserLabel
import com.intechbel.techtask.feature.users.presentation.core.UserState
import com.intechbel.techtask.presentation.components.base.StateComponent

class UserComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : StateComponent<UserState, UserLabel, UserIntent>(componentContext, storeFactory),
    IUserComponent {

    override val storeCreation: (StoreFactory) -> Store<UserIntent, UserState, UserLabel> = {
        UserStore(storeFactory = storeFactory)
    }
}

