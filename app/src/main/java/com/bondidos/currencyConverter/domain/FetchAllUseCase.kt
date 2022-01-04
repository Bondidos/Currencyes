package com.bondidos.currencyConverter.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class FetchAllUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(): Resources{
        return withContext(Dispatchers.IO){
            try {
                val responseBody = repository.fetchAll("1.4.2022")
                Resources.Success(emptyList())
            } catch (e: Exception){
                Resources.Error(e.toString())
            }
        }
    }
}
