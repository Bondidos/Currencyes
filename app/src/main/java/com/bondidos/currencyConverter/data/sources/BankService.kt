package com.bondidos.currencyConverter.data.sources

import okhttp3.Call
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface BankService {
    @GET("/Services/XmlExRates.aspx")
    fun fetchAll(
        @Query("ondate") date: String
    ): retrofit2.Response<String>

    //https://github.com/square/retrofit/tree/master/retrofit-converters/jaxb
}