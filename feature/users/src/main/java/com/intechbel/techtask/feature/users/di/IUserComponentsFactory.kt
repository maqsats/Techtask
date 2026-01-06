package com.intechbel.techtask.feature.users.di

import com.arkivanov.decompose.ComponentContext
import com.intechbel.techtask.feature.users.presentation.core.IUserComponent

interface IUserComponentsFactory {
    fun createUserComponent(
        context: ComponentContext,
    ): IUserComponent
}

