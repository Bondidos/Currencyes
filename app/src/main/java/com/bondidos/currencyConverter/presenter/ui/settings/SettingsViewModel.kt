package com.bondidos.currencyConverter.presenter.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.currencyConverter.domain.usecase.LoadDataIntoSettingsUseCase
import com.bondidos.currencyConverter.domain.util.Resources
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val loadFromCache: LoadDataIntoSettingsUseCase
): ViewModel(){

    private val _cachedList = MutableStateFlow<Resources>(Resources.Initialized)
    val cachedList: StateFlow<Resources> = _cachedList.asStateFlow()

    init {
        viewModelScope.launch {
            _cachedList.value = Resources.Loading
            _cachedList.value = loadFromCache.execute()
        }
    }
}