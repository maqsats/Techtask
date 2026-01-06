package com.intechbel.techtask.pagination

interface CustomPayload<T, B> {
    val list: List<T>
    val data: B?
}

data class CustomPayloadImpl<T, B>(
    override val list: List<T>,
    override val data: B?
) : CustomPayload<T, B>

fun <T, B> CustomPayload(list: List<T>, data: B?): CustomPayload<T, B> =
    CustomPayloadImpl(list, data)
