package com.example.jetnote.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@ExperimentalComposeUiApi
@Composable
fun NoteInputText(
    modifier: Modifier =Modifier,
    maxLines:Int =1,
    text:String,
    label:String,
    onTextChange :(String) -> Unit,
    onImeAction: () -> Unit ={}
){
    val keyboardController=LocalSoftwareKeyboardController.current

    TextField(value = text, onValueChange =onTextChange,
        label = { Text(text = label)},
        maxLines = maxLines,
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),

    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),

modifier = modifier
        )
}

@Composable
fun NoteButton(
    modifier: Modifier=Modifier,
    text:String,
    onClick:() -> Unit,
    enabled:Boolean=true
){

    Button(onClick = onClick, modifier = modifier, enabled = enabled, shape = CircleShape) {
        Text(text = text)
    }

}