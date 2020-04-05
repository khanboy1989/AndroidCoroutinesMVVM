package com.serhankhan.legocatalog.legoset.ui

import androidx.lifecycle.ViewModel
import com.serhankhan.legocatalog.legoset.data.LegoSetsRepository
import com.serhankhan.legocatalog.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class LegoSetsViewModel @Inject constructor(private val repository:LegoSetsRepository): ViewModel() {
}