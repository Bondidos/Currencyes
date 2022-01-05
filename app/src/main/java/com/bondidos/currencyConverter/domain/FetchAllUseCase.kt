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

            try {
                val dates = utils.getCalendar()
//                Log.d("UseCAse", "responseBody.toString()")
                val yesterday = repository.fetchAll(dates[0])
                val today = repository.fetchAll(dates[1])
                val result = utils.createCurrency(yesterday,today)
                        Log.d("UseCAse", result.toString())
                Resources.Success(result)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("UseCAse", e.toString())
                Resources.Error(e.toString())
            }
        }
    }
}

