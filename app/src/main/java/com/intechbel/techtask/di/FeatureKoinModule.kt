package com.intechbel.techtask.di

import com.intechbel.techtask.feature.root.di.IRootComponentsFactory
import com.intechbel.techtask.feature.root.di.RootComponentsFactory
import com.intechbel.techtask.feature.users.di.IUserComponentsFactory
import com.intechbel.techtask.feature.users.di.UserComponentsFactory
import org.koin.dsl.module

val featureModules = module {
    single<IRootComponentsFactory> { RootComponentsFactory() }
    single<IUserComponentsFactory> { UserComponentsFactory() }
}

