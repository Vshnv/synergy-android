package io.github.synergy.api

import io.github.synergy.dto.AuthenticationData
import io.github.synergy.dto.LoginDTO
import io.github.synergy.dto.RefreshDTO
import io.github.synergy.dto.RefreshResult
import io.github.synergy.dto.RegisterDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginDTO): AuthenticationData
    @POST("register")
    suspend fun register(@Body registerRequest: RegisterDTO): Response<Unit>
    @POST("refresh")
    suspend fun refresh(@Body refreshRequest: RefreshDTO): RefreshResult
}