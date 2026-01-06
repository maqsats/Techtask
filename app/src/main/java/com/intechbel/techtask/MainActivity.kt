package com.intechbel.techtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.intechbel.techtask.feature.root.di.IRootComponentsFactory
import com.intechbel.techtask.feature.root.presentation.ui.RootLayout
import com.intechbel.techtask.feature.users.di.IUserComponentsFactory
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val rootComponentsFactory: IRootComponentsFactory by inject()
    private val userComponentsFactory: IUserComponentsFactory by inject()

    private val component by lazy {
        rootComponentsFactory.createRootComponent(
            context = defaultComponentContext(),
            userComponentsFactory = userComponentsFactory,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootLayout(component = component)
                }
            }
        }
    }
}

