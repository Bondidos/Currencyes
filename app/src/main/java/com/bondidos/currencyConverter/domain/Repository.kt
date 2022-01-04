package com.bondidos.currencyConverter.domain

interface Repository {
    suspend fun fetchAll()
}