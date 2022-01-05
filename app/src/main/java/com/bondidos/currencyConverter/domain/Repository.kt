package com.bondidos.currencyConverter.domain

import com.bondidos.currencyConverter.domain.entityes.Currency
import okhttp3.Response
import retrofit2.Call


interface Repository {
    suspend fun fetchAll(date: String): List<Currency>
}