package com.intechbel.techtask.network.data.retrofit

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.BufferedSink
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class CustomConverterFactory(
    private val delegateFactory: Converter.Factory,
) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit,
    ): Converter<ResponseBody, *>? {
        // Delegate response conversion to the standard factory
        return delegateFactory.responseBodyConverter(type, annotations, retrofit)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit,
    ): Converter<Any, RequestBody>? {
        // Delegate request conversion, but modify Content-Type without charset
        val delegate = delegateFactory.requestBodyConverter(
            type,
            parameterAnnotations,
            methodAnnotations,
            retrofit
        ) as? Converter<Any, RequestBody>

        return object : Converter<Any, RequestBody> {
            override fun convert(value: Any): RequestBody? {
                // Use the delegate to convert the object to a RequestBody
                val originalBody = delegate?.convert(value)

                // If conversion was successful, return a new RequestBody with modified content type
                return object : RequestBody() {
                    override fun contentType(): MediaType? {
                        // Force the content type without charset
                        return "application/json".toMediaType()
                    }

                    override fun writeTo(sink: BufferedSink) {
                        // Delegate the actual writing of the request body
                        originalBody?.writeTo(sink)
                    }

                    override fun contentLength(): Long {
                        return originalBody?.contentLength() ?: -1
                    }
                }
            }
        }
    }
}

