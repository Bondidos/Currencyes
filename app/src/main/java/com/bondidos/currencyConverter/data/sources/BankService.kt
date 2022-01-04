package com.bondidos.currencyConverter.data.sources

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface BankService {
    @GET("Services/XmlExRates.aspx")
    fun fetchAll(
        @Query("ondate") date: String
    ): ResponseBody
}