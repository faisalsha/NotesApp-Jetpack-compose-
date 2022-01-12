package com.example.jetnote.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetnote.models.Note
import com.example.jetnote.util.DateConverter
import com.example.jetnote.util.UUIDConverter


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract  class NoteDatabase:RoomDatabase() {
    abstract fun noteDao() : NoteDatabaseDao



}