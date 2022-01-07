package com.bondidos.currencyConverter.presenter.ui.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.currencyConverter.domain.usecase.FetchActualCurrenciesUseCase
import com.bondidos.currencyConverter.domain.util.Resources
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScoped
class MainViewModel @Inject constructor(private val fetchActualCurrencies: FetchActualCurrenciesUseCase) : ViewModel() {

    private val _currencies = MutableStateFlow<Resources>(Resources.Initialized)
    val currencies: StateFlow<Resources> = _currencies.asStateFlow()

    init{
        viewModelScope.launch {
            _currencies.value = Resources.Loading
            _currencies.value = fetchActualCurrencies.execute()
        }
    }

    fun hideCurrency(curAbbreviation: String){
        /*var list: MutableList<Currencies>? = null
        viewModelScope.launch {

            _currencies.collect{
                list = if (it is Resources.Success) it.data.toMutableList() else null
            }
        }
        list?.let { list ->
            val itemToDelete = list.find { it.curAbbreviation == curAbbreviation }
            list.remove(itemToDelete)
            _currencies.value = Resources.Success(list)
        }*/
    }
}

