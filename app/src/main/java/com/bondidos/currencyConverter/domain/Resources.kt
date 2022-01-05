package com.bondidos.currencyConverter.domain

import com.bondidos.currencyConverter.domain.entityes.Currencies
import com.bondidos.currencyConverter.domain.entityes.Currency

sealed class Resources{
    object Initialized : Resources()
    object Loading : Resources()
    data class Success(
        val data: List<Currencies>
    ): Resources()
    data class Error(
        val message: String
    ): Resources()
}