package io.github.synergy.dto

import com.google.gson.annotations.SerializedName

data class AuthenticationData(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)