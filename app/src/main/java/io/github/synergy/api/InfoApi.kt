package io.github.synergy.api

import io.github.synergy.dto.Coordinates
import io.github.synergy.dto.EmergencyContact
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface InfoApi {
    @POST("location_safety_index")
    suspend fun fetchLocationSafetyIndex(@Header("Authorization") accessToken: String, @Body coordinates: Coordinates): Double
    @POST("contacts")
    suspend fun fetchContacts(@Header("Authorization") accessToken: String) : List<EmergencyContact>
    @POST("contacts/add")
    suspend fun addContact(@Header("Authorization") accessToken: String, @Body contact: EmergencyContact): Response<Unit>
    @POST("contcts/delete")
    suspend fun deleteContact(@Header("Authorization") accessToken: String, @Body contact: EmergencyContact): Response<Unit>
}