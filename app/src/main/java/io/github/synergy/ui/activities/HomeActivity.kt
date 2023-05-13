package io.github.synergy.ui.activities

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.github.synergy.AppStateProvider
import io.github.synergy.ui.screens.contacts.ContactsScreen
import io.github.synergy.ui.screens.home.HomeScreen
import io.github.synergy.ui.screens.sos.SosActiveScreen
import io.github.synergy.ui.screens.sos.SosChecklistScreen
import io.github.synergy.ui.theme.SynergyTheme
import io.github.synergy.viewmodel.ContactsViewModel
import io.github.synergy.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeActivity: ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 0)
            return
        }
        val appStateProvider = application as AppStateProvider
        val authController = appStateProvider.authController
        val activeAuthData = authController.authData
        if (activeAuthData == null) {
            startActivity(Intent(this, AuthActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
            finish()
        }
        setContent {
            SynergyTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "/home") {
                        composable("/home") {
                            val homeViewModel = viewModel<HomeViewModel>(factory = HomeViewModel.Factory(appStateProvider.infoController))
                            LaunchedEffect(Unit) {
                                fusedLocationClient.lastLocation.addOnCompleteListener {
                                    lifecycleScope.launch {
                                        homeViewModel.fetchLocationScore(it.result.latitude.toString(), it.result.longitude.toString())
                                    }
                                }
                            }
                            val score = homeViewModel.locationScore.observeAsState()
                            val location = homeViewModel.coordinates.observeAsState()
                            val contacts = viewModel<ContactsViewModel>(factory = ContactsViewModel.Factory(appStateProvider.infoController))
                            val contactsState = contacts.contacts.observeAsState()
                            HomeScreen(contactsState, authController.username ?: "User", score, location, requestSOS = {
                                navController.navigate("/sos/prepare")
                                delay(2000)
                                navController.navigate("/sos/ready")
                            }, navigateToContacts = {
                                navController.navigate("/contacts")
                            })
                        }
                        composable("/contacts") {
                            ContactsScreen()
                        }
                        composable("/sos/prepare") {
                            SosChecklistScreen()
                        }
                        composable("/sos/ready") {
                            SosActiveScreen()
                        }
                    }
                }
            }
        }
    }
}