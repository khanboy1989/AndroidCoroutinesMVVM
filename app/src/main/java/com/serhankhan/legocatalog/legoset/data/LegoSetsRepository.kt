package com.serhankhan.legocatalog.legoset.data

import com.serhankhan.legocatalog.AppExecutors
import com.serhankhan.legocatalog.ContextProviders
import com.serhankhan.legocatalog.api.LegoService
import com.serhankhan.legocatalog.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class LegoSetsRepository @Inject constructor(private val contextProviders: ContextProviders, private val appExecutors: AppExecutors, private val service: LegoService) {


}