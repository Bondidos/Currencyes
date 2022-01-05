package com.bondidos.currencyConverter.domain.entityes

data class Currencies (
    val curAbbreviation: String,
    val curName: String,
    val curScale: Int,
    val previousCurOfficialRate: Double,
    val todayCurOfficialRate: Double,
    val previousDate: String,
    val todayDate: String
)