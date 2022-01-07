package com.bondidos.currencyConverter.domain.usecase

import com.bondidos.currencyConverter.data.entities.Currency
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

            /**
            1. If cached list has the same todayDate and tomorrowDate ->
            - use cached list
            - else fetchList from Api and Update Cache

            2. cached list ->
            map to list for display (delete items with isShow == false)
            .create new class where drop no needed fields

            3. List to show -> MainViewModel
            -----------------------------------------

            Settings

            1. switched ->
            - update Currencies in room
            - delete from MainViewModel List

             */
            try {
                val tomorrowCurrency = repository.fetchCurrencies(dates[TOMORROW_DATE])
                val cache = repository.getCurrencyFromCache()
                if (isCachedListActual(tomorrowCurrency, cache)) {
                    Resources.Success(utils.removeItemsWhichShouldNotShown(cache))
                } else {
                    val apiList = createListFromApi(tomorrowCurrency)
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
        tomorrowCurrency: List<Currency>,
        cache: List<Currencies>
    ): Boolean {
        return if (tomorrowCurrency.isEmpty()) {
            cache.first().todayDate == dates[TODAY_DATE] &&
                    cache.first().alternativeDate == dates[YESTERDAY_DATE]
        } else cache.first().todayDate == dates[TODAY_DATE] &&
                cache.first().alternativeDate == dates[TOMORROW_DATE]
    }

    private suspend fun createListFromApi(tomorrowCurrency: List<Currency>): List<Currencies> {
        val todayCurrencyList = repository.fetchCurrencies(dates[TODAY_DATE])
        val alternativeDate =
            if (tomorrowCurrency.isEmpty()) repository.fetchCurrencies(dates[YESTERDAY_DATE]) else tomorrowCurrency
        return utils.createCurrency(alternativeDate, todayCurrencyList)
    }
}

