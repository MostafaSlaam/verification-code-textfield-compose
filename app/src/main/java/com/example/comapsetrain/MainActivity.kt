package com.example.comapsetrain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comapsetrain.ui.theme.ComapseTrainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComapseTrainTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    homeScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun homeScreen() {

    val state1 = rememberSaveable { mutableStateOf("") }
    val state2 = rememberSaveable { mutableStateOf("") }
    val focusRequester1 = FocusRequester()
    val focusRequester2 = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .size(75.dp)
                        .focusRequester(focusRequester = focusRequester1),
                    singleLine = true,
                    value = state1.value,
                    onValueChange = {
//                        val value = onValueChange(state.value, it)
                        if (it.isEmpty())
                            state1.value = it
                        else if (it.length == 1) {
                            state1.value = it
                            focusRequester2.requestFocus()
                        }

                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ), keyboardActions = KeyboardActions (onNext = {
                        focusRequester2.requestFocus()
                    })
                )
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .size(75.dp)
                        .focusRequester(focusRequester = focusRequester2),
                    singleLine = true,
                    value = state2.value,
                    onValueChange = {
                        if (it.isEmpty()) {
                            state2.value = it
                            focusRequester1.requestFocus()
                        }
                        else if (it.length == 1) {
                            state2.value = it

                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions (onDone = {keyboardController?.hide()})
                )

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComapseTrainTheme {

        homeScreen()
    }
}

