package com.example.practicaltask_rupalkachhela.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.practicaltask_rupalkachhela.model.AddAmount

@Dao
interface ATMDao  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addAmount(amount: AddAmount)
}