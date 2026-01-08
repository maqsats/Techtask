package com.intechbel.techtask.di

import com.intechbel.techtask.feature.root.di.IRootComponentsFactory
import com.intechbel.techtask.feature.root.di.RootComponentsFactory
import com.intechbel.techtask.feature.users.di.IUserComponentsFactory
import com.intechbel.techtask.feature.users.di.UserComponentsFactory
import com.intechbel.techtask.feature.users.di.userModules
import org.koin.dsl.bind
import org.koin.dsl.module

val featureModules = module {
    factory {
        RootComponentsFactory()
    } bind IRootComponentsFactory::class

    factory {
        UserComponentsFactory(scope = this)
    } bind IUserComponentsFactory::class

    includes(userModules)
}

