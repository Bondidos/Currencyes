package com.bondidos.currencyConverter.presenter.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.currencyConverter.domain.usecase.LoadDataIntoSettingsUseCase
import com.bondidos.currencyConverter.domain.usecase.UpdateCacheUseCase
import com.bondidos.currencyConverter.domain.util.Resources
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val loadFromCache: LoadDataIntoSettingsUseCase,
    private val update: UpdateCacheUseCase
) : ViewModel() {

    private val _cachedList = MutableStateFlow<Resources>(Resources.Initialized)
    val cachedList: StateFlow<Resources> = _cachedList.asStateFlow()

    init {
        viewModelScope.launch {
            _cachedList.value = Resources.Loading
            _cachedList.value = loadFromCache.execute()
        }
    }

    fun updateIsShowField(curAbbr: String, isShow: Boolean) {
        when (val resources = _cachedList.value) {
            is Resources.Success -> {
                resources.data
                    .find { it.curAbbreviation == curAbbr }
                    ?.let { currencies ->
                        viewModelScope.launch {
                            currencies.isShow = isShow
                            update.execute(currencies)
                        }
                    }
            }
            else -> Unit
        }
    }
}