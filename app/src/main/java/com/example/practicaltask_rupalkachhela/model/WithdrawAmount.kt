package com.example.practicaltask_rupalkachhela.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "withdraw_amount")
data class WithdrawAmount(
    @ColumnInfo(name = "amount")
    val amount: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}