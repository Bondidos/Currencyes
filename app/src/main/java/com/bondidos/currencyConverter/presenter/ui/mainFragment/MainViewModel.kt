package com.bondidos.currencyConverter.presenter.ui.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.currencyConverter.domain.usecase.FetchActualCurrenciesUseCase
import com.bondidos.currencyConverter.domain.usecase.FetchFromDatabaseUseCase
import com.bondidos.currencyConverter.domain.util.Resources
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScoped
class MainViewModel @Inject constructor(
    private val fetchActualCurrencies: FetchActualCurrenciesUseCase,
    private val fetchFromDatabase: FetchFromDatabaseUseCase
    ) : ViewModel() {

    private val _currencies = MutableStateFlow<Resources>(Resources.Initialized)
    val currencies: StateFlow<Resources> = _currencies.asStateFlow()

    init{
        viewModelScope.launch {
            _currencies.value = Resources.Loading
            _currencies.value = fetchActualCurrencies.execute()
        }
    }

    fun refresh(){
        viewModelScope.launch {
            _currencies.value = Resources.Loading
            _currencies.value = fetchFromDatabase.execute()
        }
    }
}

