package com.softwaret.kpdf.extension

import io.mockk.ConstantMatcher
import io.mockk.MockKGateway
import io.mockk.MockKMatcherScope
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

internal fun <T : Any> value(value: T): T =
    if (value::class.isInline) inlineValue(value)
    else value

@Suppress("UNCHECKED_CAST")
internal fun <T : Any> inlineValue(value: T): T {
    val valueName = value::class.primaryConstructor!!.parameters[0].name
    val valueProperty = value::class.declaredMemberProperties
        .find { it.name == valueName }!! as KProperty1<T, *>
    return valueProperty.get(value) as T
}

internal inline fun <reified T : Any> MockKMatcherScope.anyValue(): T =
    if (T::class.isInline) anyInlineValue()
    else any()


internal inline fun <reified T : Any> MockKMatcherScope.anyInlineValue(): T {
    val valueConstructor = T::class.primaryConstructor!!
    val valueType = valueConstructor.parameters[0].type.classifier as KClass<*>
    val callRecorder = getProperty("callRecorder") as MockKGateway.CallRecorder
    val anyMatcher = callRecorder.matcher(ConstantMatcher<T>(true), valueType)
    return valueConstructor.call(anyMatcher)
}

internal val KClass<*>.isInline: Boolean
    get() = !isData &&
            primaryConstructor?.parameters?.size == 1 &&
            java.declaredMethods.any { it.name == "box-impl" }