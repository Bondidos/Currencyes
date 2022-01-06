package com.bondidos.currencyConverter.domain.util

import android.annotation.SuppressLint
import com.bondidos.currencyConverter.domain.entityes.Currencies
import com.bondidos.currencyConverter.data.entities.Currency
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    fun createCurrency(altDay: List<Currency>, today: List<Currency>): List<Currencies> {
        val result = mutableListOf<Currencies>()
        val date = getCalendar()
        today.forEachIndexed { index, todayCur ->
            val altDateIndex = if(todayCur.date < altDay[index].date)
                2 else 0
            result.add(
                Currencies(
                    curAbbreviation = todayCur.curAbbreviation,
                    curName = todayCur.curName,
                    curScale = todayCur.curScale,
                    altCurOfficialRate = altDay[index].curOfficialRate,
                    todayCurOfficialRate = todayCur.curOfficialRate,
                    alternativeDate = date[altDateIndex],
                    todayDate = date[1]
                )
            )
        }
        return result.toList()
    }

    @SuppressLint("SimpleDateFormat")
    fun getCalendar(): List<String> {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val calendarToday = GregorianCalendar()
        calendarToday.isLenient = false
        calendarToday.set(year, month, day)
        val calendarYesterday = GregorianCalendar()
        calendarYesterday.isLenient = false
        calendarYesterday.set(year, month, day - 1)
        val calendarTomorrow = GregorianCalendar()
        calendarTomorrow.isLenient = false
        calendarTomorrow.set(year, month, day + 1)
        val format = SimpleDateFormat("yyyy.MM.dd", Locale("ru"))
        return listOf(
            format.format(calendarYesterday.time),
            format.format(calendarToday.time),
            format.format(calendarTomorrow.time)
        )
    }
}