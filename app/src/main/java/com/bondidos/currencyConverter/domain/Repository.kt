package com.bondidos.currencyConverter.domain

import com.bondidos.currencyConverter.domain.entityes.Currency
import com.bondidos.currencyConverter.domain.entityes.CurrencyList


interface Repository {
    suspend fun fetchAll(date: String): CurrencyList
}