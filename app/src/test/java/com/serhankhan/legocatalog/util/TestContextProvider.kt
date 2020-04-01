package com.serhankhan.legocatalog.util

import com.serhankhan.legocatalog.ContextProviders
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider:ContextProviders() {
    override val Main: CoroutineContext
        get() = Dispatchers.Unconfined
    override val IO: CoroutineContext
        get() = Dispatchers.Unconfined
}