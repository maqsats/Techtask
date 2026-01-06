package com.intechbel.techtask.presentation.components

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

typealias NoState = Unit
typealias NoIntent = Unit
typealias NoLabel = Unit
typealias NoMessage = Unit
typealias NoAction = Unit

/**
 * Marker interface for DI framework. Every Component interface must implement it.
 */
interface IStateComponent<STATE, LABEL, INTENT> : IComponent {
    val stateFlow: StateFlow<STATE>
    val labelsFlow: Flow<LABEL>

    fun accept(intent: INTENT)
}


