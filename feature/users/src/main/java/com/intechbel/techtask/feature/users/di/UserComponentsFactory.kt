package com.intechbel.techtask.feature.users.di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.intechbel.techtask.feature.users.presentation.core.IUserComponent
import com.intechbel.techtask.feature.users.presentation.logic.UserComponent

class UserComponentsFactory : IUserComponentsFactory {
    override fun createUserComponent(
        context: ComponentContext,
    ): IUserComponent {
        return UserComponent(
            componentContext = context,
            storeFactory = DefaultStoreFactory(),
        )
    }
}

