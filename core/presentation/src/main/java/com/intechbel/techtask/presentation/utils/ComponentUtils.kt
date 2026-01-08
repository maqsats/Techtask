package com.intechbel.techtask.presentation.utils

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.intechbel.techtask.presentation.components.IStateComponent

fun <STATE : Any, LABEL : Any, INTENT : Any> IStateComponent<STATE, LABEL, INTENT>.createBinder(
    componentContext: ComponentContext,
    lifecycleMode: BinderLifecycleMode = BinderLifecycleMode.START_STOP,
    consumer: suspend (LABEL) -> Unit,
) {
    bind(componentContext.lifecycle, lifecycleMode) {
        labelsFlow.bindTo(consumer)
    }
}
