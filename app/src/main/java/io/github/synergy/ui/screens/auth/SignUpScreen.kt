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
import io.github.synergy.ui.theme.AppTextHighlightDim
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(register: suspend (username: String, password: String, name: String, email: String, number: String)-> Unit = {_,_,_,_,_ -> }, navigateToLoginScreen: () -> Unit = {}) {
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
    val (name, setName) = rememberSaveable {
        mutableStateOf("")
    }
    val (email, setEmail) = rememberSaveable {
        mutableStateOf("")
    }
    val (phone, setPhone) = rememberSaveable {
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
                AnnotatedString("S", spanStyle = SpanStyle(fontSize = 40.sp))
            )
            append(
                AnnotatedString("IGN ", spanStyle = SpanStyle(fontSize = 30.sp))
            )
            append(
                AnnotatedString("U", spanStyle = SpanStyle(fontSize = 40.sp))
            )
            append(
                AnnotatedString("P", spanStyle = SpanStyle(fontSize = 30.sp))
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
            enabled = !isWaiting,
            value = username,
            onValueChange = setUsername,
            placeholder = {
                Text(text = "Username")
            })
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
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            enabled = !isWaiting,
            value = name,
            onValueChange = setName,
            placeholder = {
                Text(text = "Name")
            })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            enabled = !isWaiting,
            value = email,
            onValueChange = setEmail,
            placeholder = {
                Text(text = "Email")
            })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            enabled = !isWaiting,
            value = phone,
            onValueChange = setPhone,
            placeholder = {
                Text(text = "Phone Number")
            })
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            enabled = !isWaiting,
            onClick = {
                coroutineScope.launch {
                    setWaiting(true)
                    register(username, password, name, email, phone)
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
            Text(text = "Register")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = buildAnnotatedString {
            append(
                AnnotatedString("Already have an account? ", spanStyle = SpanStyle(fontSize = 15.sp))
            )
            append(
                AnnotatedString("Login!", spanStyle = SpanStyle(fontSize = 15.sp, color = AppTextHighlight))
            )
        }, color = Color.White, modifier = Modifier.clickable { navigateToLoginScreen() })
    }
}


@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}