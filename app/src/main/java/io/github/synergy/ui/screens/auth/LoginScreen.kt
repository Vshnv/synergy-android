package io.github.synergy.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.synergy.ui.theme.AppBlack
import io.github.synergy.ui.theme.AppGrey
import io.github.synergy.ui.theme.AppTextHighlight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(login: suspend (username: String, password: String) -> Unit = {_,_ ->}, navigateToSignUpScreen: () -> Unit = {}) {
    val coroutineScope = rememberCoroutineScope()
    val verticalScrollState = rememberScrollState()
    val (isWaiting, setWaiting) = rememberSaveable {
        mutableStateOf(false)
    }
    val (username, setUsername) = rememberSaveable {
        mutableStateOf("")
    }
    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }
    val (showPassword, setShowPassword) = rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBlack)
            .padding(10.dp)
            .verticalScroll(verticalScrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = buildAnnotatedString {
            append(
                AnnotatedString("L", spanStyle = SpanStyle(fontSize = 40.sp))
            )
            append(
                AnnotatedString("OGIN", spanStyle = SpanStyle(fontSize = 30.sp))
            )
        }, color = Color.White)
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            Modifier
                .fillMaxWidth(0.9f)
                .height(5.dp)
                .background(AppTextHighlight)
        ) {}
        Spacer(modifier = Modifier.height(2.dp))
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = username,
            onValueChange = setUsername,
            placeholder = {
            Text(text = "Username")
        }, enabled = !isWaiting)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            enabled = !isWaiting,
            value = password,
            onValueChange = setPassword,
            trailingIcon = {
                val image = if (showPassword)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (showPassword) "Hide password" else "Show password"

                IconButton(onClick = {setShowPassword(!showPassword)}){
                    Icon(imageVector  = image, description)
                }
            },
            placeholder = {
            Text(text = "Password")
        }, visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            enabled = !isWaiting,
            onClick = {
               coroutineScope.launch {
                   setWaiting(true)
                   login(username, password)
                   setWaiting(false)
               }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTextHighlight,
                contentColor = Color.White,
                disabledContainerColor = AppGrey,
                disabledContentColor = Color.White
            )
        ) {
            if (isWaiting) {
                CircularProgressIndicator()
            } else {
                Text(text = "Login")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = buildAnnotatedString {
            append(
                AnnotatedString("Don't have an account? ", spanStyle = SpanStyle(fontSize = 15.sp))
            )
            append(
                AnnotatedString("Sign Up!", spanStyle = SpanStyle(fontSize = 15.sp, color = AppTextHighlight))
            )
        }, color = Color.White,
        modifier = Modifier.clickable {
            navigateToSignUpScreen()
        })
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}