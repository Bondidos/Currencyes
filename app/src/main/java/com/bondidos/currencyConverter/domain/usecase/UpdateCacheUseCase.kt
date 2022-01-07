package com.bondidos.currencyConverter.domain.usecase

import com.bondidos.currencyConverter.domain.Repository
import javax.inject.Inject

class UpdateCacheUseCase @Inject constructor(private val repository: Repository) {

    suspend fun execute(){
        TODO("Implement")
    }
}