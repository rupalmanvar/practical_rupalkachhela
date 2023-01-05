package com.example.practicaltask_rupalkachhela.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicaltask_rupalkachhela.model.WithdrawAmount

@Dao
interface WithdrawAmountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWithDrawAmount(withdrawAmount: WithdrawAmount)

    @Query("SELECT SUM(amount) AS total FROM withdraw_amount")
    fun getTotalWithdrawAmount():Int

    @Query("SELECT * FROM withdraw_amount ORDER BY id DESC LIMIT 5;")
    fun getTransactionList(): List<WithdrawAmount>
}