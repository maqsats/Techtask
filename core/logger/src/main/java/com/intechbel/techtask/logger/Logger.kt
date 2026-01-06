package com.intechbel.techtask.logger

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

object Logger {

    fun init(isDebug: Boolean) {
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashlyticsTree())
        }
    }

    /** Debug-уровень (видно только в Logcat) */
    fun d(message: String) {
        Timber.d(message)
    }

    /** Info-уровень (видно только в Logcat) */
    fun i(message: String) {
        Timber.i(message)
    }

    /** Warning-уровень (видно только в Logcat) */
    fun w(message: String, t: Throwable? = null) {
        if (t != null) {
            Timber.w(t, message)
        } else {
            Timber.w(message)
        }
    }

    /** Error-уровень (все исключения/сообщения уйдут в CrashlyticsTree) */
    fun e(throwable: Throwable, message: String? = null) {
        Timber.e(throwable, message)
    }
}

class CrashlyticsTree : Timber.Tree(), KoinComponent {
    private val crashlytics: FirebaseCrashlytics by inject()

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority < Log.ERROR) return

        crashlytics.log("${tag ?: "Logger"}: $message")
        t?.let { crashlytics.recordException(it) }
    }
}

