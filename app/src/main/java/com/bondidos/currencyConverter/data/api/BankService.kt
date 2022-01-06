package com.bondidos.currencyConverter.data.api

import com.bondidos.currencyConverter.data.entities.Currency
import retrofit2.http.GET
import retrofit2.http.Query

interface BankService {
    @GET("/api/exrates/rates?periodicity=0")
    suspend fun fetchAll(
        @Query("ondate") date: String
    ): List<Currency>
}