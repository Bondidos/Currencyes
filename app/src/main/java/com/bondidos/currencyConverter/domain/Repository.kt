package com.bondidos.currencyConverter.domain

import okhttp3.Response
import okhttp3.ResponseBody

interface Repository {
    suspend fun fetchAll(date: String): retrofit2.Response<String>
}