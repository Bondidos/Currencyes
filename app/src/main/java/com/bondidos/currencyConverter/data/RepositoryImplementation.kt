package com.bondidos.currencyConverter.data

import com.bondidos.currencyConverter.data.api.BankService
import com.bondidos.currencyConverter.data.room_storage.CurrencyDao
import com.bondidos.currencyConverter.domain.Repository
import com.bondidos.currencyConverter.domain.entityes.Currencies
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val webService: BankService,
    private val roomStorage: CurrencyDao
) : Repository {

    override suspend fun fetchCurrencies(date: String) = webService.fetchAll(date)

    override suspend fun saveCurrencyToCache(currency: List<Currencies>) =
        roomStorage.saveCurrenciesToCache(currency)


    override suspend fun getCurrencyFromCache(): List<Currencies> =
        roomStorage.getCurrenciesFromCache()

    override suspend fun updateCurrencyState(currencies: Currencies) {
        roomStorage.updateCurrencyState(currencies)
    }
}