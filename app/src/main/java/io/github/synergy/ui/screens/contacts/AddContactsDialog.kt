package io.github.synergy.ui.screens.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.synergy.ui.theme.AppGrey
import io.github.synergy.ui.theme.AppTextHighlight
import io.github.synergy.ui.theme.AppTextHighlightDim
import io.github.synergy.ui.theme.AppWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactsDialog() {
    val (name, setName) = remember { mutableStateOf("") }
    val (number, setNumber) = remember { mutableStateOf("") }
    Column(
        Modifier
            .border(2.dp, AppTextHighlightDim, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(AppGrey)
            .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Add Contact", fontSize = 20.sp, color = AppWhite)
        Column(Modifier.padding(10.dp)) {
            Text(text = "Name", fontSize = 15.sp, color = AppTextHighlight)
            TextField(value = name, onValueChange = setName)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Phone no.", fontSize = 15.sp, color = AppTextHighlight)
            TextField(value = number, onValueChange = setNumber)
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTextHighlight,
                contentColor = Color.White,
                disabledContainerColor = AppGrey,
                disabledContentColor = Color.White
            )
        ) {
            Text(text = "Add", fontSize = 15.sp, color = AppWhite)
        }
    }
}


@Preview
@Composable
fun AddContactsDialogPreview() {
    AddContactsDialog()
}