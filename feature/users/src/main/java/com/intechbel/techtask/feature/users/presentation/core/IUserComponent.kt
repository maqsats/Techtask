package com.intechbel.techtask.feature.users.presentation.core

import com.intechbel.techtask.presentation.components.IStateComponent

interface IUserComponent : IStateComponent<UserState, UserLabel, UserIntent> {
    // Additional user-specific methods can be added here
}

