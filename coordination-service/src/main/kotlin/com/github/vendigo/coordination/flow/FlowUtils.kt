package com.github.vendigo.coordination.flow

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

fun FlowContext.load(init: FlowContext.() -> Unit) {
    init(this)
}

fun <T> FlowContext.resolver(resolver: AsyncResolver<T>): Lazy<Deferred<T>> = lazy {
    val flowContext = this
    this.scope.async {
        resolver.resolve(flowContext)
    }
}

interface AsyncResolver<T> {
    suspend fun resolve(context: FlowContext): T
}