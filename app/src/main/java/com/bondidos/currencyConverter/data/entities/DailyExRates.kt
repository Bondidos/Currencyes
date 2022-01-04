package com.bondidos.currencyConverter.data.entities

import com.bondidos.currencyConverter.domain.entityes.Currency
import java.util.*

data class DailyExRates (
    val currency: List<Currency>,
    val date: Date,
    val text: String
)

