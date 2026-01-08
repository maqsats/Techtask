package com.intechbel.techtask.feature.root.di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.intechbel.techtask.feature.root.presentation.core.IRootComponent
import com.intechbel.techtask.feature.root.presentation.logic.RootComponent
import com.intechbel.techtask.shared.di.provide
import org.koin.core.component.KoinComponent

class RootComponentsFactory : IRootComponentsFactory, KoinComponent {
    override fun createRootComponent(
        context: ComponentContext,
    ): IRootComponent {
        return RootComponent(
            componentContext = context,
            storeFactory = DefaultStoreFactory(),
            userComponentsFactory = provide(),
        )
    }
}

