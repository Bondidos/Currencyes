package com.bondidos.currencyConverter.domain.usecase

import com.bondidos.currencyConverter.domain.Repository
import com.bondidos.currencyConverter.domain.util.Resources
import com.bondidos.currencyConverter.domain.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchFromDatabaseUseCase @Inject constructor(
    private val repository: Repository,
    private val utils: Utils
) {
    suspend fun execute(): Resources {
        return withContext(Dispatchers.IO){
            try {
                val cache = repository.getCurrencyFromCache()
                Resources.Success(utils.removeItemsWhichShouldNotShown(cache))
            } catch (e: Exception){
                Resources.Error(e.toString())
            }
        }
    }
}