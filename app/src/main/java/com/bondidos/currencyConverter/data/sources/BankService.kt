package com.bondidos.currencyConverter.data.sources

import com.bondidos.currencyConverter.domain.entityes.Currency
import com.bondidos.currencyConverter.domain.entityes.CurrencyList
import retrofit2.http.GET
import retrofit2.http.Query

interface BankService {
    @GET("/api/exrates/rates?periodicity=0")
    suspend fun fetchAll(
        @Query("ondate") date: String
    ): CurrencyList
}