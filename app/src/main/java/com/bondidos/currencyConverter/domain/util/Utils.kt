package com.bondidos.currencyConverter.domain.util

import android.annotation.SuppressLint
import com.bondidos.currencyConverter.domain.entityes.Currencies
import com.bondidos.currencyConverter.domain.entityes.Currency
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    fun createCurrency(previousDay: List<Currency>, today: List<Currency>): List<Currencies> {
        val result = mutableListOf<Currencies>()
        val time = getCalendar()
        today.forEachIndexed { index, todayCur ->
            result.add(
                Currencies(
                    curAbbreviation = todayCur.curAbbreviation,
                    curName = todayCur.curName,
                    curScale = todayCur.curScale,
                    previousCurOfficialRate = previousDay[index].curOfficialRate,
                    todayCurOfficialRate = todayCur.curOfficialRate,
                    previousDate = time[0],
                    todayDate = time[1]
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
        val format = SimpleDateFormat("yyyy.MM.dd", Locale("ru"))
        return listOf(
            format.format(calendarYesterday.time),
            format.format(calendarToday.time)
        )
    }
}