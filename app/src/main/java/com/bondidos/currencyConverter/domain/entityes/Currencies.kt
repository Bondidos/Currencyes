package com.bondidos.currencyConverter.domain.entityes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Currency")
data class Currencies (
    @PrimaryKey(autoGenerate = false)
    val curAbbreviation: String,
    val curName: String,
    val curScale: Int,
    val altCurOfficialRate: Double?,
    val todayCurOfficialRate: Double?,
    val alternativeDate: String,
    val todayDate: String,
    var isShow: Boolean = true
)