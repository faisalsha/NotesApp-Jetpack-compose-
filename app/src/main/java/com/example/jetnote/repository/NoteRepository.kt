package com.example.jetnote.repository

import com.example.jetnote.database.NoteDatabaseDao
import com.example.jetnote.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository  @Inject constructor(private val noteDatabaseDao :NoteDatabaseDao) {

    suspend fun addNote(note: Note) = noteDatabaseDao.insertNote(note = note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note=note)
    suspend fun deleteNote(note: Note) =noteDatabaseDao.deleteNote(note=note)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()
    fun getAllNotes() :Flow<List<Note>> =noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}