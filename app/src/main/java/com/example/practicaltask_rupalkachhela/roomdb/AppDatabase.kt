package com.example.practicaltask_rupalkachhela.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicaltask_rupalkachhela.model.AddAmount
import com.example.practicaltask_rupalkachhela.model.Notes
import com.example.practicaltask_rupalkachhela.model.WithdrawAmount

@Database(
    entities = [AddAmount::class, WithdrawAmount::class, Notes::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAmountDao(): ATMDao

}