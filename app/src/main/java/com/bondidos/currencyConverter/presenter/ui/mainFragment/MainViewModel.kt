package com.bondidos.currencyConverter.presenter.ui.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.currencyConverter.domain.entityes.Currencies
import com.bondidos.currencyConverter.domain.usecase.FetchAllUseCase
import com.bondidos.currencyConverter.domain.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScoped
class MainViewModel @Inject constructor(private val fetchAllUseCase: FetchAllUseCase) : ViewModel() {

    private val _currencies = MutableStateFlow<Resources>(Resources.Initialized)
    val currencies: StateFlow<Resources> = _currencies.asStateFlow()

    init{
        viewModelScope.launch {
            _currencies.value = Resources.Loading
            _currencies.value = fetchAllUseCase.execute()
        }
    }

    fun hideCurrency(curAbbreviation: String){
        var list: MutableList<Currencies>? = null
        viewModelScope.launch {

            _currencies.collect{
                list = if (it is Resources.Success) it.data.toMutableList() else null
            }
        }
        list?.let { list ->
            val itemToDelete = list.find { it.curAbbreviation == curAbbreviation }
            list.remove(itemToDelete)
            _currencies.value = Resources.Success(list)
        }
    }
}

