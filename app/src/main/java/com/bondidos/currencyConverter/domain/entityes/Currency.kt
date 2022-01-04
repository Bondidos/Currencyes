package com.bondidos.currencyConverter.domain.entityes

data class Currency(
    val NumCode: Int,
    val CharCode: String,
    val Scale: Int,
    val Name: String,
    val Rate: Double,
    val Id: Int,
    val text: String
)