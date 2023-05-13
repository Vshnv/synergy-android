package io.github.synergy.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.synergy.dto.Coordinates
import io.github.synergy.dto.EmergencyContact
import io.github.synergy.ui.theme.AppBlack
import io.github.synergy.ui.theme.AppGrey
import io.github.synergy.ui.theme.AppTextHighlight
import io.github.synergy.ui.theme.AppTextHighlightDim
import io.github.synergy.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(contacts: State<List<EmergencyContact>?>, username: String, score: State<Double?>, location: State<Coordinates?>, requestSOS: suspend () -> Unit, navigateToContacts: () -> Unit) {
    val verticalScrollState = rememberScrollState()
    val coroutineScore = rememberCoroutineScope()
    Column(modifier = Modifier
        .verticalScroll(verticalScrollState)
        .fillMaxSize()
        .background(AppBlack)
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        UserInfoBar(
            modifier = Modifier
                .fillMaxWidth(),
            username = username,
            coordinates = location.value
        )
        EmergencyButton {
            coroutineScore.launch {
                requestSOS()
            }
        }
        LocationSafetyIndexMeter(
            score = score.value ?: 0.00,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp)
        )
        RecentEmergencyWidget(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .heightIn(min = 100.dp)
        )
        EmergencyContactsWidget(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .heightIn(min = 100.dp),
            onMore = {
                navigateToContacts()
            },
            contacts = contacts.value ?: emptyList()
        )
    }
}

@Composable
fun UserInfoBar(modifier: Modifier = Modifier, username: String, coordinates: Coordinates?) {
    Row(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .background(AppGrey)
        .padding(10.dp)) {
        Column() {
            Text(text = "Hello, $username", color = Color.White, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Complete your profile", color = AppTextHighlight, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            Text(text = "Last Location", color = Color.White, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = coordinates?.let { it.latitude + ", " + it.longitude } ?: "N/A", color = AppTextHighlight, fontSize = 12.sp)
        }
    }
}

@Composable
fun EmergencyButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(20.dp)
            .border(8.dp, AppTextHighlightDim, shape = CircleShape)
            .padding(15.dp)
            .size(150.dp),
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTextHighlight,
            contentColor = Color.White,
            disabledContainerColor = AppGrey,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "S.O.S", fontSize = 20.sp)
    }
}

@Composable
fun LocationSafetyIndexMeter(score: Double, modifier: Modifier = Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Location Safety Index", color = Color.White, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = String.format("%.2f", score), color = Color.White, fontSize = 32.sp)
        Spacer(modifier = Modifier.height(10.dp))
        LinearProgressIndicator(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp), color = AppTextHighlight, trackColor = AppTextHighlightDim, progress = (score/10.0).toFloat())
    }
}


@Preview
@Composable
fun HomeScreenPreview() {

    HomeScreen(contacts = remember { mutableStateOf(listOf()) }, username = "Test", score = remember { mutableStateOf(0.0) }, location = remember { mutableStateOf(null) }, requestSOS =  {}) {

    }
}