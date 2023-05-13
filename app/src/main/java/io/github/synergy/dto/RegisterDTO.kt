package io.github.synergy.dto

import com.google.gson.annotations.SerializedName

data class RegisterDTO(
    val username: String,
    val password: String,
    val email: String,
    val name: String,
    @SerializedName("phone_number")
    val phoneNo: String
)