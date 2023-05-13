package io.github.synergy.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.synergy.dto.EmergencyContact
import io.github.synergy.ui.theme.AppGrey
import io.github.synergy.ui.theme.AppTextHighlight
import io.github.synergy.ui.theme.AppTextHighlightDim
import io.github.synergy.ui.theme.AppWhite


@Composable
fun EmergencyContactsWidget(modifier: Modifier = Modifier, onMore: () -> Unit, contacts: List<EmergencyContact>) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .background(AppGrey)
        .padding(10.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Emergency Contacts", color = Color.White, fontSize = 15.sp)
            Text(text = "More", color = Color.Cyan, fontSize = 12.sp)
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            EmergencyContact(modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp), name = "Vaishnav Anil", "+919605182938")
            EmergencyContact(modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp), name = "Jeff Biju", "+919072299900")
            AddEmergencyContactButton(modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp))

        }
    }
}

@Composable
fun EmergencyContact(modifier: Modifier, name: String, number: String, showDelete: Boolean = false) {
    val contactCharacter = remember(name) {
        name.firstOrNull()?.uppercase() ?: "E"
    }
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier
                .border(8.dp, AppTextHighlightDim, shape = CircleShape)
                .padding(5.dp)
                .size(50.dp)
                .clip(CircleShape)
                .background(AppTextHighlight),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = contactCharacter,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Column(modifier = Modifier.padding(10.dp, 0.dp)) {
            Text(text = name, color = Color.White, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = number, color = AppTextHighlight, fontSize = 12.sp)
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Phone, contentDescription = "Call", tint = Color.White)
            }
            if (showDelete) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Remove",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun AddEmergencyContactButton(modifier: Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Call", tint = Color.White)
        }
        Column(modifier = Modifier.padding(10.dp, 0.dp)) {
            Text(text = "Click to add", color = AppWhite, fontSize = 14.sp)
        }
    }
}
