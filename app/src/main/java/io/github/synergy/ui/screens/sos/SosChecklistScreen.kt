package io.github.synergy.ui.screens.sos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.synergy.ui.theme.AppBlack
import io.github.synergy.ui.theme.AppTextHighlightDim

@Composable
fun SosChecklistScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(AppBlack), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier, verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.Start) {
            ChecklistItem(text = "Alerting Emergency Services", checked = false)
            Spacer(modifier = Modifier.height(25.dp))
            ChecklistItem(text = "Alerting Nearby Users", checked = false)
            Spacer(modifier = Modifier.height(25.dp))
            ChecklistItem(text = "Alerting Emergency Contacts", checked = false)
        }
    }
}

@Composable
fun ChecklistItem(text: String, checked: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (checked) {
            Icon(modifier = Modifier
                .clip(CircleShape)
                .background(AppTextHighlightDim)
                .padding(5.dp), imageVector = Icons.Filled.Check, contentDescription = "Done", tint = Color.White)
        } else {
            CircularProgressIndicator(color = AppTextHighlightDim)
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = text, color = Color.White, fontSize = 15.sp)
    }
}

@Preview
@Composable
fun SosChecklistScreenPreview() {
    SosChecklistScreen()
}


@Preview
@Composable
fun ChecklistItemUncheckedPreview() {
    ChecklistItem("Contacting emergency contacts", false)
}

@Preview
@Composable
fun ChecklistItemCheckedPreview() {
    ChecklistItem("Contacting emergency contacts", true)
}