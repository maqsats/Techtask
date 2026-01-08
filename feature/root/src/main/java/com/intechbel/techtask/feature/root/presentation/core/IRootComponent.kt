package com.intechbel.techtask.feature.root.presentation.core

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.intechbel.techtask.feature.users.presentation.core.IUserComponent
import com.intechbel.techtask.feature.users.presentation.core.IUserDetailsComponent
import com.intechbel.techtask.presentation.components.IStateComponent

interface IRootComponent : IStateComponent<RootState, RootLabel, RootIntent> {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Users(val component: IUserComponent) : Child
        data class UserDetails(val component: IUserDetailsComponent) : Child
    }
}

