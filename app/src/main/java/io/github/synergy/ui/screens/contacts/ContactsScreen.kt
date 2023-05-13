package io.github.synergy.ui.screens.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.synergy.ui.screens.home.AddEmergencyContactButton
import io.github.synergy.ui.screens.home.EmergencyContact
import io.github.synergy.ui.theme.AppBlack
import io.github.synergy.ui.theme.AppWhite

@Composable
fun ContactsScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(AppBlack), verticalArrangement = Arrangement.Top) {
        EmergencyContact(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), name = "Tyson", number = "+919605182938", showDelete = true)
        EmergencyContact(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), name = "Tyson", number = "+919605182938", showDelete = true)
    }
}


@Preview(name = "ContactsScreen")
@Composable
fun ContactsScreenPreview() {
    ContactsScreen()
}