package com.intechbel.techtask.feature.root.presentation.logic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.intechbel.techtask.feature.root.presentation.core.IRootComponent
import com.intechbel.techtask.feature.root.presentation.core.RootConfiguration
import com.intechbel.techtask.feature.root.presentation.core.RootIntent
import com.intechbel.techtask.feature.root.presentation.core.RootLabel
import com.intechbel.techtask.feature.root.presentation.core.RootState
import com.intechbel.techtask.feature.users.di.IUserComponentsFactory
import com.intechbel.techtask.presentation.components.base.StateComponent

class RootComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val userComponentsFactory: IUserComponentsFactory,
) : StateComponent<RootState, RootLabel, RootIntent>(componentContext, storeFactory),
    IRootComponent {

    private val navigation = StackNavigation<RootConfiguration>()

    override val childStack: Value<ChildStack<*, IRootComponent.Child>> = childStack(
        source = navigation,
        serializer = null,
        initialConfiguration = RootConfiguration.Users,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: RootConfiguration,
        componentContext: ComponentContext
    ): IRootComponent.Child =
        when (config) {
            is RootConfiguration.Users -> IRootComponent.Child.Users(
                userComponentsFactory.createUserComponent(componentContext)
            )
        }

    override val storeCreation: (StoreFactory) -> Store<RootIntent, RootState, RootLabel> = {
        RootStore(storeFactory = storeFactory)
    }
}

