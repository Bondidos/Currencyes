package com.bondidos.currencyConverter.domain

import com.bondidos.currencyConverter.data.entities.Currency


interface Repository {
    suspend fun fetchAll(date: String): List<Currency>
}