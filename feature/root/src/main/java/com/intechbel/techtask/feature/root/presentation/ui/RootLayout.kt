package com.intechbel.techtask.feature.root.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.intechbel.techtask.feature.root.presentation.core.IRootComponent
import com.intechbel.techtask.feature.users.presentation.ui.UserDetailsLayout
import com.intechbel.techtask.feature.users.presentation.ui.UserLayout

@Composable
fun RootLayout(
    component: IRootComponent,
    modifier: Modifier = Modifier,
) {
    val childStack = component.childStack.subscribeAsState()
    val activeChild = childStack.value.active.instance

    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        when (activeChild) {
            is IRootComponent.Child.Users -> {
                UserLayout(
                    component = activeChild.component,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is IRootComponent.Child.UserDetails -> {
                UserDetailsLayout(
                    component = activeChild.component,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

