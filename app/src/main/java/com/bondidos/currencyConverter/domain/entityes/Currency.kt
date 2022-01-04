package com.bondidos.currencyConverter.domain.entityes

data class Currency(
    val id: Int,
    val name: String,
    val scale: Int,
    val previousRate: Double,
    val presentRate: Double
)