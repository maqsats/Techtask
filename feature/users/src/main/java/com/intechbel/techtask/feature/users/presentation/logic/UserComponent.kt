package com.intechbel.techtask.feature.users.presentation.logic

import androidx.paging.PagingData
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.feature.users.domain.paging.UserPagingSource
import com.intechbel.techtask.feature.users.presentation.core.IUserComponent
import com.intechbel.techtask.feature.users.presentation.core.UserIntent
import com.intechbel.techtask.feature.users.presentation.core.UserLabel
import com.intechbel.techtask.feature.users.presentation.core.UserState
import com.intechbel.techtask.presentation.components.base.StateComponent
import com.intechbel.techtask.shared.di.Provider
import kotlinx.coroutines.flow.StateFlow

class UserComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val pagingSource: Provider<UserPagingSource>
) : StateComponent<UserState, UserLabel, UserIntent>(componentContext, storeFactory),
    IUserComponent {

    override val usersPagingFlow: StateFlow<PagingData<User>>
        get() = pagingSource.use.paging

    override val storeCreation: (StoreFactory) -> Store<UserIntent, UserState, UserLabel> = {
        UserStore(storeFactory = storeFactory, pagingSource = pagingSource.use)
    }
}

