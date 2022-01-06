package com.bondidos.currencyConverter.presenter.di

import android.content.Context
import androidx.room.Room
import com.bondidos.currencyConverter.data.RepositoryImplementation
import com.bondidos.currencyConverter.data.api.BankService
import com.bondidos.currencyConverter.data.room_storage.AppDatabase
import com.bondidos.currencyConverter.data.room_storage.CurrencyDao
import com.bondidos.currencyConverter.domain.Repository
import com.bondidos.currencyConverter.domain.constants.Constants.BASE_URL
import com.bondidos.currencyConverter.domain.util.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(): BankService {
        /* val moshi = Moshi.Builder()
             .add(KotlinJsonAdapterFactory())
             .build()*/

        return Retrofit.Builder()
            //  .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(BankService::class.java)
    }

    /*@Singleton
    @Provides
    fun provideNBRBService(retrofit: Retrofit): BankService =
        retrofit.create(BankService::class.java)*/

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CurrencyDao =
        Room.databaseBuilder(context,AppDatabase::class.java, "database").build().getDatabase()

    @Singleton
    @Provides
    fun provideRepository(bankService: BankService, database: CurrencyDao): Repository =
        RepositoryImplementation(bankService,database)

    @Provides
    fun provideUtils(): Utils = Utils()
}
