package com.bondidos.currencyConverter.domain

import com.bondidos.currencyConverter.domain.entityes.Currency

class FetchAllUseCase (private val repository: Repository) {
    fun execute(): Resources{
        return Resources.Error("Not Implemented")
    }
}
