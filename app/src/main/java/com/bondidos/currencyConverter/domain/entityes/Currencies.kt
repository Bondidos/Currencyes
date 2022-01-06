package com.bondidos.currencyConverter.domain.entityes

data class Currencies (
    val curAbbreviation: String,
    val curName: String,
    val curScale: Int,
    val altCurOfficialRate: Double,
    val todayCurOfficialRate: Double,
    val alternativeDate: String,
    val todayDate: String
)