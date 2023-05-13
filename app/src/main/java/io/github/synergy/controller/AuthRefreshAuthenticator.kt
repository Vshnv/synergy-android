package io.github.synergy.controller

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AuthRefreshAuthenticator(private val authController: AuthController): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            if (authController.refreshSession()) {
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${authController.authData!!.accessToken}")
                    .build()
            } else {
                null
            }
        }
    }
}