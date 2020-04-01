package com.serhankhan.legocatalog

import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
open class ContextProviders {
    open val Main: CoroutineContext = Dispatchers.Main
    open val IO: CoroutineContext = Dispatchers.IO

    companion object {
        @Volatile
        private var INSTANCE: ContextProviders? = null

        fun getInstance() : ContextProviders {
            return INSTANCE?: synchronized(this) {
                ContextProviders()
            }.also {
                INSTANCE = it
            }
        }
    }
}