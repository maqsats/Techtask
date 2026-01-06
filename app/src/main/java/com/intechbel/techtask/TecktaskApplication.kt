package com.intechbel.techtask

import android.app.Application
import com.intechbel.techtask.di.featureModules
import com.intechbel.techtask.logger.Logger
import com.intechbel.techtask.network.networkModules
import com.intechbel.techtask.presentation.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class TecktaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@TecktaskApplication)

            // Core modules
            modules(networkModules)
            modules(presentationModules)
            
            // Feature modules
            modules(featureModules)
        }
    }

    private fun initLogger() {
        Logger.init(BuildConfig.DEBUG)
    }
}

