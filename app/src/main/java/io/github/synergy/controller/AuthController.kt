package io.github.synergy.controller

import android.content.SharedPreferences
import io.github.synergy.api.AuthApi
import io.github.synergy.dto.AuthenticationData
import io.github.synergy.dto.LoginDTO
import io.github.synergy.dto.RefreshDTO
import io.github.synergy.dto.RegisterDTO

class AuthController(private val authApi: AuthApi, private val preferences: SharedPreferences) {
    companion object {
        private const val USERNAME_KEY = "auth.username"
        private const val ACCESS_TOKEN_KEY = "auth.access_token"
        private const val REFRESH_TOKEN_KEY = "auth.refresh_token"
    }
    private var authDataCache: AuthenticationData? = null
    val username: String?
        get() {
            return preferences.getString(USERNAME_KEY, "User")
        }
    val authData: AuthenticationData?
        get() {
            if (authDataCache != null) {
                return authDataCache
            }
            val refreshToken = preferences.getString(REFRESH_TOKEN_KEY, null)
            val accessToken = preferences.getString(ACCESS_TOKEN_KEY, null)
            if (refreshToken == null) {
                return null
            }
            authDataCache = AuthenticationData(
                accessToken!!,
                refreshToken
            )
            return authDataCache
        }

    suspend fun login(username: String, password: String): Boolean {
        val loginResult = kotlin.runCatching {
            authApi.login(LoginDTO(username = username, password = password))
        }
        if (loginResult.isSuccess) {
            saveLoginDetails(loginResult.getOrNull()!!, username)
            return true
        }
        return false
    }

    suspend fun register(username: String, password: String, email: String, name: String, phoneNo: String): Boolean {
        val registerResult = authApi.register(RegisterDTO(
            username, password, email, name, phoneNo
        ))
        return registerResult.isSuccessful
    }

    suspend fun refreshSession(): Boolean {
        val authData = authData ?: return false
        val refreshResult = kotlin.runCatching {
            authApi.refresh(RefreshDTO(
                authData.refreshToken
            ))
        }
        if (refreshResult.isFailure) return false
        val result = refreshResult.getOrNull()
        val updatedAccessToken = result?.accessToken ?: return false
        updateAccessToken(updatedAccessToken)
        return true
    }

    private fun saveLoginDetails(authenticationData: AuthenticationData, username: String) {
        authDataCache = authenticationData
        preferences.edit()
            .putString(USERNAME_KEY, username)
            .putString(ACCESS_TOKEN_KEY, authenticationData.accessToken)
            .putString(REFRESH_TOKEN_KEY, authenticationData.refreshToken)
            .apply()
    }

    private fun clearLoginDetails() {
        authDataCache = null
        preferences.edit()
            .putString(USERNAME_KEY, null)
            .putString(ACCESS_TOKEN_KEY, null)
            .putString(REFRESH_TOKEN_KEY, null)
            .commit()
    }

    private fun updateAccessToken(accessToken: String) {
        authDataCache = AuthenticationData(
            accessToken = accessToken,
            refreshToken = authDataCache?.refreshToken ?: ""
        )
        preferences.edit()
            .putString(ACCESS_TOKEN_KEY, accessToken)
            .commit()
    }
}