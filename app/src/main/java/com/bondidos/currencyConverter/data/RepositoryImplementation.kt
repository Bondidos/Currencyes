package com.bondidos.currencyConverter.data

import com.bondidos.currencyConverter.data.sources.BankService
import com.bondidos.currencyConverter.domain.Repository
import retrofit2.Retrofit
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val webService: BankService
): Repository {

    override suspend fun fetchAll(date: String) = webService.fetchAll(date)
}