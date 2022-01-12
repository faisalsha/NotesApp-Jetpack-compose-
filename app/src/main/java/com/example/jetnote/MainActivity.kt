package com.example.jetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.models.Note
import com.example.jetnote.screens.NoteScreen
import com.example.jetnote.screens.NoteViewModel
import com.example.jetnote.ui.theme.JetNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                val notesViewModel = viewModel<NoteViewModel>()
                    NotesApp(notesViewModel)

                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun NotesApp(notesViewModel: NoteViewModel= viewModel()){
    val notesList=notesViewModel.noteList.collectAsState().value
    NoteScreen(notes = notesList, onDeleteNote = {
      notesViewModel.removeNote(it)
    }, onAddNote = {
       notesViewModel.addNote(it)
    })
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetNoteTheme {

    }
}