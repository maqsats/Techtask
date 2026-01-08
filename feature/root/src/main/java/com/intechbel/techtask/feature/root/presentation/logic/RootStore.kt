package com.intechbel.techtask.feature.root.presentation.logic

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.intechbel.techtask.feature.root.presentation.core.RootIntent
import com.intechbel.techtask.feature.root.presentation.core.RootLabel
import com.intechbel.techtask.feature.root.presentation.core.RootState

class RootStore(
    storeFactory: StoreFactory,
) : Store<RootIntent, RootState, RootLabel> by storeFactory.create(
    name = RootStore::class.simpleName,
    initialState = RootState.initial(),
    executorFactory = {
        RootExecutor()
    }
)

