package com.serhankhan.legocatalog.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.serhankhan.legocatalog.ContextProviders
import com.serhankhan.legocatalog.api.ApiEmptyResponse
import com.serhankhan.legocatalog.api.ApiErrorResponse
import com.serhankhan.legocatalog.api.ApiResponse
import com.serhankhan.legocatalog.api.ApiSuccessResponse
import com.serhankhan.legocatalog.vo.Resource
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.coroutineContext

abstract class NetworkResourceBound<ResultType, RequestType>
constructor(private val contextProviders: ContextProviders) {

    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        result.postValue(Resource.loading(null))
        fetchNetworkFromNetwork()
    }

    private fun fetchNetworkFromNetwork() {
        CoroutineScope(contextProviders.IO).launch {
            val apiResponse = createCall()
            CoroutineScope(contextProviders.Main).launch {
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)
                    when (response) {
                        is ApiSuccessResponse -> {
                            setValue(Resource.success(processResponse(response)))
                        }
                        is ApiErrorResponse -> {
                            onFetchFailed()
                            setValue(Resource.error(response.errorMessage, null))
                        }
                        is ApiEmptyResponse -> {
                            setValue(Resource.error("An empty value returned", null))
                        }
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    fun asLiveData() = result as LiveData<Resource<RequestType>>

}