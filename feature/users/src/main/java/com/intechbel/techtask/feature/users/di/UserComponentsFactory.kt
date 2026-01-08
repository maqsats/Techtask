package com.intechbel.techtask.feature.users.di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.intechbel.techtask.feature.users.presentation.logic.UserComponent
import com.intechbel.techtask.feature.users.presentation.logic.UserDetailsComponent
import com.intechbel.techtask.shared.di.provide
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope
import com.intechbel.techtask.feature.users.domain.model.User

class UserComponentsFactory(override val scope: Scope) :
    IUserComponentsFactory, KoinScopeComponent {

    override fun createUserComponent(
        context: ComponentContext,
    ) = UserComponent(
        componentContext = context,
        storeFactory = DefaultStoreFactory(),
        pagingSource = provide(),
    )

    override fun createUserDetailsComponent(
        context: ComponentContext,
        user: User,
    ) = UserDetailsComponent(
        componentContext = context,
        storeFactory = DefaultStoreFactory(),
        user = user,
    )
}

