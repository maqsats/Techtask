package com.intechbel.techtask.feature.users.presentation.logic

import com.arkivanov.decompose.ComponentContext
import com.intechbel.techtask.feature.users.presentation.core.IUserDetailsComponent
import com.intechbel.techtask.feature.users.presentation.core.UserDetailsIntent
import com.intechbel.techtask.feature.users.presentation.core.UserDetailsLabel
import com.intechbel.techtask.feature.users.presentation.core.UserDetailsState
import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.presentation.components.base.StateComponent
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory

class UserDetailsComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val user: User,
) : StateComponent<UserDetailsState, UserDetailsLabel, UserDetailsIntent>(componentContext, storeFactory),
    IUserDetailsComponent {

    override val storeCreation: (StoreFactory) -> Store<UserDetailsIntent, UserDetailsState, UserDetailsLabel> =
        { factory ->
            UserDetailsStore(
                storeFactory = factory,
                user = user,
            )
        }
}
