package io.github.synergy.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.synergy.ui.theme.AppGrey
import io.github.synergy.ui.theme.AppTextHighlight
import io.github.synergy.ui.theme.AppTextHighlightDim

@Composable
fun RecentEmergencyWidget(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .background(AppGrey)
        .padding(10.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Recent SOS Calls", color = Color.White, fontSize = 15.sp)
            Text(text = "More", color = Color.Cyan, fontSize = 12.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
            RecentSOSCall()
        }
    }
}

@Composable
fun RecentSOSCall() {
    Column(
        modifier = Modifier
            .border(8.dp, AppTextHighlightDim, shape = RoundedCornerShape(10.dp))
            .padding(5.dp)
            .size(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AppTextHighlight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "V",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
