package com.example.practicaltask_rupalkachhela.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicaltask_rupalkachhela.model.AddAmount

@Dao
interface ATMDao : WithdrawAmountDao,NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addAmount(amount: AddAmount)

    @Query("SELECT SUM(amount) AS total FROM add_amount")
    fun getTotalBalance(): Int
}