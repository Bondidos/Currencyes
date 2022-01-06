package com.bondidos.currencyConverter.domain.usecase

import com.bondidos.currencyConverter.domain.Repository
import com.bondidos.currencyConverter.domain.util.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadDataIntoSettingsUseCase @Inject constructor(
    private val repository: Repository
){
    suspend fun execute():Resources{
        // here should to get list from db and map it to display in settings screen
        return withContext(Dispatchers.IO){
            try {
                Resources.Success(repository.getCurrencyFromCache())
            } catch (e: Exception){
                Resources.Error(e.toString())
            }
        }
    }
}