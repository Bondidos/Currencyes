package com.bondidos.currencyConverter.presenter.di

import com.bondidos.currencyConverter.data.RepositoryImplementation
import com.bondidos.currencyConverter.data.sources.BankService
import com.bondidos.currencyConverter.domain.Repository
import com.bondidos.currencyConverter.domain.constants.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jaxb.JaxbConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(JaxbConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideNBRBService(retrofit: Retrofit): BankService =
        retrofit.create(BankService::class.java)

    @Singleton
    @Provides
    fun provideRepository(bankService: BankService): Repository =
        RepositoryImplementation(bankService)
}