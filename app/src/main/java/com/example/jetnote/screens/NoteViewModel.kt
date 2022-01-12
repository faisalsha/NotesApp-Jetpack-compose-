package com.example.jetnote.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.models.Note
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository):ViewModel() {
//   private var noteList=
//        mutableStateListOf<Note>()

    private val _noteList=MutableStateFlow<List<Note>>(emptyList())
    val noteList=_noteList.asStateFlow()


    init {
//        noteList.addAll(NotesDataSource().loadNotes())
        viewModelScope.launch (Dispatchers.IO){
            repository.getAllNotes().distinctUntilChanged().collect {listOfNotes ->
                if(listOfNotes.isNullOrEmpty()){
                    println("empty")
                }else{
                    _noteList.value=listOfNotes
                }

            }
        }
    }

  fun addNote(note:Note){
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

 fun updateNote(note: Note) =viewModelScope.launch { repository.updateNote(note) }


 fun removeNote(note: Note){
       viewModelScope.launch {
           repository.deleteNote(note)
       }

    }

//    fun getAllNotes():List<Note>{
//        return noteList
//    }
}