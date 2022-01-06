package com.bondidos.currencyConverter.data.room_storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bondidos.currencyConverter.domain.entityes.Currencies

@Database(entities = [Currencies::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDatabase(): CurrencyDao
}