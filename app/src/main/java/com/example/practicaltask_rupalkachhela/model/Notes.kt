package com.example.practicaltask_rupalkachhela.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Notes(
    @ColumnInfo(name = "notes")
    val note: String,
    @ColumnInfo(name = "number_of_notes")
    val numberOfNotes: Int,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}