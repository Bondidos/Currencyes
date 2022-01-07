package com.bondidos.currencyConverter.domain.usecase

import com.bondidos.currencyConverter.domain.Repository
import com.bondidos.currencyConverter.domain.util.Resources
import com.bondidos.currencyConverter.domain.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class FetchActualCurrenciesUseCase @Inject constructor(
    private val repository: Repository,
    private val utils: Utils
) {
    suspend fun execute(): Resources {
        return withContext(Dispatchers.IO) {

            val dates = utils.getCalendar()

            try {
                val tomorrow = repository.fetchCurrencies(dates[2])

                val alternativeDate =
                    if (tomorrow.isEmpty()) repository.fetchCurrencies(dates[0]) else tomorrow

                val today = repository.fetchCurrencies(dates[1])
                val apiList = utils.createCurrency(alternativeDate, today)
                val cacheList = repository.getCurrencyFromCache()
                val result = utils.updateIsShowField(apiList, cacheList)
                repository.saveCurrencyToCache(result)

                Resources.Success(result)
            } catch (e: Exception) {
                Resources.Error("Не удалось получить курсы валют")
            }
        }
    }
}

