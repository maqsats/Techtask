package com.intechbel.techtask.presentation.components.base

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.intechbel.techtask.presentation.components.IStateComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class StateComponent<STATE : Any, LABEL : Any, INTENT : Any>(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : Component(componentContext), IStateComponent<STATE, LABEL, INTENT> {
    abstract val storeCreation: (StoreFactory) -> Store<INTENT, STATE, LABEL>

    @OptIn(ExperimentalCoroutinesApi::class)
    override val stateFlow: StateFlow<STATE>
        get() = store.stateFlow

    override val labelsFlow: Flow<LABEL>
        get() = store.labels

    private val storeKey = "${this::class.java.name}#store"

    protected val store: Store<INTENT, STATE, LABEL> by lazy {
        instanceKeeper.getStore(key = storeKey) {
            storeCreation.invoke(storeFactory)
        }
    }

    override fun accept(intent: INTENT) {
        store.accept(intent)
    }
}


