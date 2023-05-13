package io.github.synergy.controller

import android.content.Context
import android.content.Intent
import io.github.synergy.api.InfoApi
import io.github.synergy.dto.Coordinates
import io.github.synergy.dto.EmergencyContact
import io.github.synergy.ui.activities.AuthActivity
import io.github.synergy.ui.activities.HomeActivity

class InfoController(context: Context, private val infoApi: InfoApi, private val authController: AuthController) {
    private val context = context.applicationContext
    suspend fun fetchLocationSafetyIndex(latitude: String, longitude: String): Double {
        val authData = authController.authData
        if (authData == null) {
            context.startActivity(Intent(context, AuthActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
            return -1.0
        }
        val score = kotlin.runCatching {
            infoApi.fetchLocationSafetyIndex(
                "Bearer ${authController.authData!!.accessToken}",
                Coordinates(
                    latitude, longitude
                )
            )
        }
        return score.getOrNull() ?: -1.0
    }

    suspend fun fetchContacts(): List<EmergencyContact> {
        val authData = authController.authData
        if (authData == null) {
            context.startActivity(Intent(context, AuthActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
            return emptyList()
        }
        val contacts = kotlin.runCatching {
            infoApi.fetchContacts(
                "Bearer ${authController.authData!!.accessToken}"
            )
        }
        return contacts.getOrNull() ?: emptyList()
    }

    suspend fun addContact(contact: EmergencyContact): Boolean {
        val authData = authController.authData
        if (authData == null) {
            context.startActivity(Intent(context, AuthActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
            return false
        }

        return kotlin.runCatching {
            infoApi.addContact(
                "Bearer ${authController.authData!!.accessToken}",
                contact
            )
        }.getOrNull()?.isSuccessful ?: false
    }

    suspend fun deleteContact(contact: EmergencyContact): Boolean {
        val authData = authController.authData
        if (authData == null) {
            context.startActivity(Intent(context, AuthActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
            return false
        }

        return kotlin.runCatching {
            infoApi.deleteContact(
                "Bearer ${authController.authData!!.accessToken}",
                contact
            )
        }.getOrNull()?.isSuccessful ?: false
    }
}