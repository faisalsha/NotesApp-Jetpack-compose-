package com.example.jetnote.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.*


@Entity(tableName = "notes_tbl")
data class Note(

    @PrimaryKey
    val id:UUID= UUID.randomUUID(),

    @ColumnInfo(name = "notes_title")
    val title:String,

    @ColumnInfo(name = "notes_description")
    val description:String,

    @ColumnInfo(name = "notes_entry_date")
    val entryDate:Date = Date.from(Instant.now())
)
