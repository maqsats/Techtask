package com.intechbel.techtask.shared.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

inline fun <reified T : Any> Scope.provide(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    noinline parameters: ParametersDefinition? = null,
) = object : Provider<T> {
    override val use: T by inject<T>(
        qualifier,
        mode,
        parameters,
    )
}

inline fun <reified T : Any> KoinComponent.provide(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    noinline parameters: ParametersDefinition? = null,
) = object : Provider<T> {
    override val use: T by inject<T>(
        qualifier,
        mode,
        parameters,
    )
}

inline fun <reified T : Any> Koin.provide(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    noinline parameters: ParametersDefinition? = null,
) = object : Provider<T> {
    override val use: T by inject<T>(
        qualifier,
        mode,
        parameters,
    )
}

inline fun <reified T> provideRaw(crossinline provider: () -> T) = object : Provider<T> {
    override val use: T by lazy {
        provider.invoke()
    }
}
