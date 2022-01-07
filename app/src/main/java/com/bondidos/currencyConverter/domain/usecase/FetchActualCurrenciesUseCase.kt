package com.bondidos.currencyConverter.domain.usecase

import com.bondidos.currencyConverter.domain.Repository
import com.bondidos.currencyConverter.domain.constants.Constants.TODAY_DATE
import com.bondidos.currencyConverter.domain.constants.Constants.TOMORROW_DATE
import com.bondidos.currencyConverter.domain.constants.Constants.YESTERDAY_DATE
import com.bondidos.currencyConverter.domain.entityes.Currencies
import com.bondidos.currencyConverter.domain.util.Resources
import com.bondidos.currencyConverter.domain.util.Utils
import com.bondidos.currencyConverter.domain.util.updateIsShowField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class FetchActualCurrenciesUseCase @Inject constructor(
    private val repository: Repository,
    private val utils: Utils
) {

    private val dates = utils.getCalendar()

    suspend fun execute(): Resources {
        return withContext(Dispatchers.IO) {

            try {
                val cache = repository.getCurrencyFromCache()
                if (isCachedListActual(cache)) {
                    Resources.Success(utils.removeItemsWhichShouldNotShown(cache))
                } else {
                    val apiList = createListFromApi()
                    apiList.updateIsShowField(cache)
                    repository.saveCurrencyToCache(apiList)
                    Resources.Success(utils.removeItemsWhichShouldNotShown(apiList))
                }
            } catch (e: Exception) {
                Resources.Error("Не удалось получить курсы валют")
            }
        }
    }

    private fun isCachedListActual(
        cache: List<Currencies>
    ): Boolean {
        val controlItem = cache.first()
        return controlItem.alternativeDate == dates[TOMORROW_DATE] &&
            controlItem.todayDate == dates[TODAY_DATE] ||
            controlItem.todayDate == dates[TODAY_DATE] &&
            controlItem.alternativeDate == dates[YESTERDAY_DATE]
    }

    private suspend fun createListFromApi(): List<Currencies> {
        val tomorrowCurrency = repository.fetchCurrencies(dates[TOMORROW_DATE])
        val todayCurrencyList = repository.fetchCurrencies(dates[TODAY_DATE])
        val alternativeDate =
            if (tomorrowCurrency.isEmpty()) repository.fetchCurrencies(dates[YESTERDAY_DATE]) else tomorrowCurrency
        return utils.createCurrency(alternativeDate, todayCurrencyList)
    }
}
