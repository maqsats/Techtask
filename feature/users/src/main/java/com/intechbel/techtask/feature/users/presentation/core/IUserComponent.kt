package com.intechbel.techtask.feature.users.presentation.core

import androidx.paging.PagingData
import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.presentation.components.IStateComponent
import kotlinx.coroutines.flow.StateFlow

interface IUserComponent : IStateComponent<UserState, UserLabel, UserIntent> {
    val usersPagingFlow: StateFlow<PagingData<User>>
}

