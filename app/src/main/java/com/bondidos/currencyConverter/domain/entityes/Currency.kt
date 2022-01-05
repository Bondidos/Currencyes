package com.bondidos.currencyConverter.domain.entityes


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    @Json(name = "Cur_Abbreviation")
    val curAbbreviation: String,
    @Json(name = "Cur_ID")
    val curID: Int,
    @Json(name = "Cur_Name")
    val curName: String,
    @Json(name = "Cur_OfficialRate")
    val curOfficialRate: Double,
    @Json(name = "Cur_Scale")
    val curScale: Int,
    @Json(name = "Date")
    val date: String
)