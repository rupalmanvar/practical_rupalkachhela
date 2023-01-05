package com.example.practicaltask_rupalkachhela.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "add_amount")
data class AddAmount(
    @ColumnInfo(name = "amount")
    val amount: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}



