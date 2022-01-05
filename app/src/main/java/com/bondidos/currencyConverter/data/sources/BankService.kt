package com.bondidos.currencyConverter.data.sources

import com.bondidos.currencyConverter.domain.entityes.Currency
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BankService {
    @GET("/api/exrates/rates?periodicity=0")
    suspend fun fetchAll(
        @Query("ondate") date: String
    ): List<Currency>
}