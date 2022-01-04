package com.bondidos.currencyConverter.domain

import okhttp3.ResponseBody

interface Repository {
    suspend fun fetchAll(date: String): ResponseBody
}