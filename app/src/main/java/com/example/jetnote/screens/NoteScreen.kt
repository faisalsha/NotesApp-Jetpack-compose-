package com.example.jetnote.screens

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.GravityCompat
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.models.Note
import com.example.jetnote.util.formatDate
import java.time.format.DateTimeFormatter

@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes:List<Note>,
    onAddNote:(Note) ->Unit,
    onDeleteNote:(Note) ->Unit
){
    val context= LocalContext.current
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription ="icon" )
            },
            backgroundColor = MaterialTheme.colors.secondaryVariant

            )
        
        //content
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(text = title, label = "Title", onTextChange = {
                                                                        if(it.all { char->
                                                                                char.isLetter() || char.isWhitespace()
                                                                            })title=it


            }, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            NoteInputText(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),  text = description, label = "Add a Note", onTextChange = {
                if(it.all { char->
                        char.isLetter() || char.isWhitespace()
                    })description=it
            })
            

            
            NoteButton(text = "Save", onClick = {

                if(title.isNotEmpty()&& description.isNotEmpty()){
                    //save data
                        onAddNote(Note(title=title, description = description,))

                    title=""
                    description=""

                    Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
                }else{

                    Toast.makeText(context,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                }
                 })




        }

        Divider(Modifier.padding(10.dp))

//        if(notes.isEmpty()){
//
//            Text(   modifier = Modifier.align(alignment = Alignment.CenterHorizontally),text= "No Data Found", style = MaterialTheme.typography.h4)
//
//        }else{

            LazyColumn{
                items(notes){note->
                    println(note.toString())
                    println(notes.size)





                    NoteRow(note = note, onNoteClicked = {
                        onDeleteNote(note)
                    })








                }

        }

        
    }

}

@Composable
fun NoteRow(
    modifier: Modifier=Modifier,
    note:Note,
    onNoteClicked:(Note) ->Unit
){
    Surface(modifier = Modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(topStart = 33.dp, bottomEnd = 33.dp,))
        .fillMaxWidth(),
        color = Color.LightGray,
        elevation = 5.dp


    ) {
        Column(
            Modifier
                .padding(horizontal = 14.dp, vertical = 6.dp)
                .clickable {
                    onNoteClicked(note)
                }, horizontalAlignment = Alignment.Start) {


            
            
            Text(text = note.title, style = MaterialTheme.typography.subtitle1)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.caption)
//            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")), style = MaterialTheme.typography.caption)

        }
        
    }

}




@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NotesScreen(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onDeleteNote = {})

}