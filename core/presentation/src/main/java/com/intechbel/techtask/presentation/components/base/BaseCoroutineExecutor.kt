package com.intechbel.techtask.presentation.components.base

import com.arkivanov.mvikotlin.core.annotations.MainThread
import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.store.Executor.Callbacks
import com.arkivanov.mvikotlin.core.utils.internal.InternalMviKotlinApi
import com.arkivanov.mvikotlin.core.utils.internal.atomic
import com.arkivanov.mvikotlin.core.utils.internal.initialize
import com.arkivanov.mvikotlin.core.utils.internal.requireValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import java.util.concurrent.ConcurrentHashMap

@OptIn(InternalMviKotlinApi::class)
open class BaseCoroutineExecutor<in Intent : Any, Action : Any, State : Any, Message : Any, Label : Any>(
    mainContext: CoroutineContext = Dispatchers.Main,
) : Executor<Intent, Action, State, Message, Label> {
    private val jobs: MutableMap<String, Job> = ConcurrentHashMap()

    private val callbacks = atomic<Callbacks<State, Message, Action, Label>>()

    /**
     * A [CoroutineScope] that can be used by the [BaseCoroutineExecutor] descendants to launch coroutines.
     * The [CoroutineScope] is automatically cancelled on dispose.
     */
    protected val scope: CoroutineScope =
        CoroutineScope(
            SupervisorJob() + mainContext
        )

    final override fun init(callbacks: Callbacks<State, Message, Action, Label>) {
        this.callbacks.initialize(callbacks)
    }

    protected fun state(): State = callbacks.requireValue().state

    override fun executeIntent(intent: Intent) {
        // no-op - override in subclasses
    }

    override fun executeAction(action: Action) {
        // no-op - override in subclasses
    }

    override fun dispose() {
        cancelSingleJobs()
        scope.cancel()
    }

    protected fun forward(action: Action) {
        callbacks.requireValue().onAction(action)
    }

    @MainThread
    protected fun dispatch(message: Message) {
        callbacks.requireValue().onMessage(message)
    }

    protected suspend fun dispatchMain(message: Message) = withContext(Dispatchers.Main.immediate) {
        callbacks.requireValue().onMessage(message)
    }

    @MainThread
    protected fun publish(label: Label) {
        callbacks.requireValue().onLabel(label)
    }

    protected suspend fun publishMain(label: Label) = withContext(Dispatchers.Main.immediate) {
        callbacks.requireValue().onLabel(label)
    }

    protected fun CoroutineScope.launchSingle(key: String, action: suspend CoroutineScope.() -> Unit) {
        tryCancelSingleJob(key) {
            val job = launch(block = action)
            jobs[key] = job
        }
    }

    protected fun tryCancelSingleJob(jobKey: String, onSucceededCanceling: (() -> Unit)? = null) {
        val job = jobs[jobKey]
        job?.cancel()
        jobs.remove(jobKey)
        onSucceededCanceling?.invoke()
    }

    private fun cancelSingleJobs() {
        jobs.forEach {
            it.value.cancel()
        }
        jobs.clear()
    }
}


