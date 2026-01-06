package com.intechbel.techtask.shared

import com.intechbel.techtask.shared.common_models.ErrorType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed interface ApiResult<out T> {

    /**
     * Represents a network result that successfully received a response containing body data.
     */
    class Success<T>(val data: T) : ApiResult<T>

    /**
     * Represents a network result that was successfully completed without containing body data.
     */
    class SuccessNoResponse<T> : ApiResult<T>

    /**
     * Represents a network result that successfully received a response containing an error message.
     */
    class Error<T>(
        val message: UiText,
        val code: Int? = null,
        val messageBody: String? = null,
        val type: ErrorType? = null,
    ) :
        ApiResult<T>

    @OptIn(ExperimentalContracts::class)
    companion object {

        inline fun <T> ApiResult<T>.onSuccess(action: (value: T) -> Unit): ApiResult<T> {
            contract {
                callsInPlace(action, InvocationKind.AT_MOST_ONCE)
            }
            if (this is Success) action(data)
            return this
        }

        inline fun <T> ApiResult<T>.onSuccessNoResponse(action: () -> Unit): ApiResult<T> {
            contract {
                callsInPlace(action, InvocationKind.AT_MOST_ONCE)
            }
            if (this is SuccessNoResponse) action()
            return this
        }

        inline fun <T, R> ApiResult<T>.convertOnSuccess(action: (value: T) -> R): ApiResult<R> {
            contract {
                callsInPlace(action, InvocationKind.AT_MOST_ONCE)
            }
            return when (this) {
                is Error -> Error(message, code, messageBody, type)
                is Success -> Success(action(data))
                is SuccessNoResponse -> SuccessNoResponse()
            }
        }

        @JvmName("functionToConvertWithoutParsing")
        inline fun <T> ApiResult<T>.onError(
            action: (message: UiText, code: Int?, messageBody: String?) -> Unit,
        ): ApiResult<T> {
            contract {
                callsInPlace(action, InvocationKind.AT_MOST_ONCE)
            }
            if (this is Error) action(message, code, messageBody)
            return this
        }

        @JvmName("functionToConvertWithoutParsing")
        inline fun <T> ApiResult<T>.onError(
            action: (message: UiText, code: Int?, messageBody: String?, errorType: ErrorType?) -> Unit,
        ): ApiResult<T> {
            contract {
                callsInPlace(action, InvocationKind.AT_MOST_ONCE)
            }
            if (this is Error) action(message, code, messageBody, type)
            return this
        }

        @OptIn(ExperimentalSerializationApi::class)
        @JvmName("functionToConvertWithParsing")
        inline fun <reified E : Any, T> ApiResult<T>.onError(
            action: (message: UiText, code: Int?, parsedError: E?) -> Unit,
        ): ApiResult<T> {
            contract {
                callsInPlace(action, InvocationKind.AT_MOST_ONCE)
            }

            if (this is Error) {
                val code = code
                val message = message
                val parsedError: E? = try {
                    messageBody?.let {
                        val json = Json {
                            ignoreUnknownKeys = true
                            explicitNulls = false
                        }
                        json.decodeFromString<E>(it)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }

                action(message, code, parsedError)
            }
            return this
        }

        @JvmName("doActionOnFail")
        inline fun <T, R> ApiResult<T>.doActionOnNetworkError(
            onSuccess: (value: Success<T>) -> ApiResult<R>,
            onNetworkError: (value: Error<R>) -> ApiResult<R>,
        ): ApiResult<R> {
            contract {
                callsInPlace(onSuccess, InvocationKind.AT_MOST_ONCE)
                callsInPlace(onNetworkError, InvocationKind.AT_MOST_ONCE)
            }

            return when (this) {
                is Success -> {
                    onSuccess(this)
                }

                is Error -> {
                    val error = Error<R>(this.message, this.code, this.messageBody, this.type)
                    if (type == ErrorType.NO_NETWORK) {
                        onNetworkError(error)
                    } else {
                        error
                    }
                }

                else ->
                    SuccessNoResponse()
            }
        }
    }
}

fun <T> ApiResult<T>.toContentUiState(): ContentUiState<T> {
    return when (this) {
        is ApiResult.Success -> ContentUiState.Success(data)
        is ApiResult.SuccessNoResponse -> ContentUiState.SuccessNoResponse
        is ApiResult.Error -> ContentUiState.Error(message, type)
    }
}

