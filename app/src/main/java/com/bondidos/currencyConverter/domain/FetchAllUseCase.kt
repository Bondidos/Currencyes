package com.bondidos.currencyConverter.domain

import android.util.Log
import com.bondidos.currencyConverter.domain.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class FetchAllUseCase @Inject constructor(
    private val repository: Repository,
    private val utils: Utils
) {
    suspend fun execute(): Resources {
        return withContext(Dispatchers.IO) {

            val dates = utils.getCalendar()
            try {
                val tomorrow = repository.fetchAll(dates[2])
                val alternativeDate = if (tomorrow.isEmpty())
                    repository.fetchAll(dates[0]) else tomorrow
                val today = repository.fetchAll(dates[1])
                val result = utils.createCurrency(alternativeDate, today)
//                        Log.d("UseCAse", result.toString())
                Resources.Success(result)
            } catch (e: Exception) {
                Resources.Error("Не удалось получить курсы валют")
            }
        }
    }
}

