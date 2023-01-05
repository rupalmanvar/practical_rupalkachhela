package com.example.practicaltask_rupalkachhela.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicaltask_rupalkachhela.model.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getAllNotes(): List<Notes>

    @Query("UPDATE notes SET number_of_notes=:numberOfNotes WHERE notes=:notes")
    fun updateNotes(numberOfNotes: Int, notes: String)
}