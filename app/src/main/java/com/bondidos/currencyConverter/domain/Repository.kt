package com.bondidos.currencyConverter.domain

import com.bondidos.currencyConverter.data.entities.Currency
import com.bondidos.currencyConverter.domain.entityes.Currencies


interface Repository {
    suspend fun fetchAll(date: String): List<Currency>
    suspend fun saveCurrencyToCache(currency: List<Currencies>)
    suspend fun getCurrencyFromCache(): List<Currencies>
}