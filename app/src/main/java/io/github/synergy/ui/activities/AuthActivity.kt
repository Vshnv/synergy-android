package io.github.synergy.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.synergy.AppStateProvider
import io.github.synergy.ui.screens.auth.LoginScreen
import io.github.synergy.ui.screens.auth.SignUpScreen
import io.github.synergy.ui.theme.SynergyTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appStateProvider = application as AppStateProvider
        val authController = appStateProvider.authController
        val activeAuthData = authController.authData
        if (activeAuthData != null) {
            startActivity(Intent(this, HomeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
            finish()
        }
        setContent {
            SynergyTheme {
                val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "/login") {
                        composable("/login") {
                            LoginScreen(navigateToSignUpScreen = {
                                navController.popBackStack()
                                navController.navigate("/signup")
                            }, login = { username, password ->
                                if (authController.login(username, password)) {
                                    Toast.makeText(this@AuthActivity, "Login successfull!", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@AuthActivity, HomeActivity::class.java).apply {
                                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    })
                                } else {
                                    Toast.makeText(this@AuthActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                        composable("/signup") {
                            SignUpScreen(navigateToLoginScreen = {
                                navController.popBackStack()
                                navController.navigate("/login")
                            }, register = { username, password, name, email, number ->
                                if (authController.register(username, password, name, email, number)) {
                                    Toast.makeText(this@AuthActivity, "Registration successfull!", Toast.LENGTH_SHORT).show()
                                    if (authController.login(username, password)) {
                                        startActivity(Intent(this@AuthActivity, HomeActivity::class.java).apply {
                                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                        })
                                    } else {
                                        navController.navigate("/login")
                                    }
                                } else {
                                    Toast.makeText(this@AuthActivity, "Registration failed!", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}