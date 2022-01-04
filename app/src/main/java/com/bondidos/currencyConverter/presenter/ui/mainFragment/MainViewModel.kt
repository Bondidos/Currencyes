package com.bondidos.currencyConverter.presenter.ui.mainFragment

import androidx.lifecycle.ViewModel
import com.bondidos.currencyConverter.domain.FetchAllUseCase
import com.bondidos.currencyConverter.domain.Resources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val fetchAllUseCase: FetchAllUseCase) : ViewModel() {

    private val _currencies = MutableStateFlow<Resources>(Resources.Initialized)
    val currencies: StateFlow<Resources> = _currencies.asStateFlow()

    init{
        _currencies.value = fetchAllUseCase.execute()
    }
}

