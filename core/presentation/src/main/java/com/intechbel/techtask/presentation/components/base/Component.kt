package com.intechbel.techtask.presentation.components.base

import com.arkivanov.decompose.ComponentContext
import com.intechbel.techtask.presentation.components.IComponent

abstract class Component(
    private val context: ComponentContext,
) : ComponentContext by context, IComponent


