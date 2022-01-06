package com.bondidos.currencyConverter.presenter.ui.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.currencyConverter.domain.usecase.FetchAllUseCase
import com.bondidos.currencyConverter.domain.util.Resources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val fetchAllUseCase: FetchAllUseCase) : ViewModel() {

    private val _currencies = MutableStateFlow<Resources>(Resources.Initialized)
    val currencies: StateFlow<Resources> = _currencies.asStateFlow()

    init{
        viewModelScope.launch {
            _currencies.value = Resources.Loading
            _currencies.value = fetchAllUseCase.execute()
        }
    }
}

