package com.intechbel.techtask.feature.root.di

import com.arkivanov.decompose.ComponentContext
import com.intechbel.techtask.feature.root.presentation.core.IRootComponent

interface IRootComponentsFactory {
    fun createRootComponent(
        context: ComponentContext
    ): IRootComponent
}

