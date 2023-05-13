package io.github.synergy.ui.screens.sos

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import io.github.synergy.R
import io.github.synergy.ui.theme.AppBlack
import io.github.synergy.ui.theme.AppGrey
import io.github.synergy.ui.theme.AppTextHighlight
import io.github.synergy.ui.theme.AppTextHighlightDim
import io.github.synergy.ui.theme.AppWhite

@Composable
fun SosActiveScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_emergency))
    val progress by animateLottieCompositionAsState(composition, reverseOnRepeat = true, iterations =  LottieConstants.IterateForever)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(AppBlack), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(AppGrey)
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "S.O.S Active", color = AppWhite, fontSize = 20.sp, fontWeight = FontWeight.Normal)
        }
        Column(
            Modifier
                .height(300.dp)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.padding(20.dp)
            .border(5.dp, AppTextHighlightDim, shape = RoundedCornerShape(25.dp))
            .padding(5.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTextHighlight,
                contentColor = Color.White,
                disabledContainerColor = AppGrey,
                disabledContentColor = Color.White
            )) {
            Text(text = "Guide to Safe Point")
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTextHighlightDim,
                contentColor = Color.White,
                disabledContainerColor = AppGrey,
                disabledContentColor = Color.White
            )) {
            Text(text = "Alarm", fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(AppGrey)
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Let us know when you are safe", color = AppWhite, fontSize = 15.sp, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.height(20.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppTextHighlight,
                        contentColor = Color.White,
                        disabledContainerColor = AppGrey,
                        disabledContentColor = Color.White
                    )) {
                    Text(text = "I am safe now", fontSize = 15.sp)
                }
            }
        }
    }
}


@Preview
@Composable
fun SosActiveScreenPreview() {
    SosActiveScreen()
}