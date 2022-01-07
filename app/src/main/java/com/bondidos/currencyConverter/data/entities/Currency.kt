package com.bondidos.currencyConverter.data.entities

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("Cur_Abbreviation")
    val curAbbreviation: String,
    @SerializedName("Cur_ID")
    val curID: Int,
    @SerializedName("Cur_Name")
    val curName: String,
    @SerializedName("Cur_OfficialRate")
    val curOfficialRate: Double?,
    @SerializedName("Cur_Scale")
    val curScale: Int,
    @SerializedName("Date")
    val date: String
)