package com.bondidos.currencyConverter.data.room_storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bondidos.currencyConverter.domain.entityes.Currencies

@Dao
interface CurrencyDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveCurrenciesToCache(list: List<Currencies>)

    @Query("select * from currency")
    suspend fun getCurrenciesFromCache(): List<Currencies>
}