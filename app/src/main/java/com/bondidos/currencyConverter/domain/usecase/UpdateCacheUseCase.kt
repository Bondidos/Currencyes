package com.bondidos.currencyConverter.domain.usecase

import com.bondidos.currencyConverter.domain.Repository
import com.bondidos.currencyConverter.domain.entityes.Currencies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateCacheUseCase @Inject constructor(private val repository: Repository) {

    suspend fun execute(currencies: Currencies){
        withContext(Dispatchers.IO){
            repository.updateCurrencyState(currencies)
        }
    }
}