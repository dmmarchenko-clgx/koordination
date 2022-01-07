package com.github.vendigo.coordination.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

fun <T> lazyAsync(scope: CoroutineScope, initializer: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> = lazy {
    scope.async {
        initializer()
    }
}