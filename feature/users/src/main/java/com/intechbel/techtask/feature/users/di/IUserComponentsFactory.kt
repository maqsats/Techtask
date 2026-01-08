package com.intechbel.techtask.feature.users.di

import com.arkivanov.decompose.ComponentContext
import com.intechbel.techtask.feature.users.presentation.core.IUserComponent
import com.intechbel.techtask.feature.users.presentation.core.IUserDetailsComponent
import com.intechbel.techtask.feature.users.domain.model.User

interface IUserComponentsFactory {
    fun createUserComponent(
        context: ComponentContext,
    ): IUserComponent

    fun createUserDetailsComponent(
        context: ComponentContext,
        user: User,
    ): IUserDetailsComponent
}

