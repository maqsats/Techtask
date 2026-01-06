package com.intechbel.techtask.feature.root.di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.intechbel.techtask.feature.root.presentation.core.IRootComponent
import com.intechbel.techtask.feature.root.presentation.logic.RootComponent
import com.intechbel.techtask.feature.users.di.IUserComponentsFactory

class RootComponentsFactory : IRootComponentsFactory {
    override fun createRootComponent(
        context: ComponentContext,
        userComponentsFactory: IUserComponentsFactory,
    ): IRootComponent {
        return RootComponent(
            componentContext = context,
            storeFactory = DefaultStoreFactory(),
            userComponentsFactory = userComponentsFactory,
        )
    }
}

