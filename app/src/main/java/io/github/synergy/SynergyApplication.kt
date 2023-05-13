package io.github.synergy

import android.app.Application
import android.content.SharedPreferences
import io.github.synergy.api.AuthApi
import io.github.synergy.api.InfoApi
import io.github.synergy.controller.AuthController
import io.github.synergy.controller.InfoController
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SynergyApplication: Application(), AppStateProvider {
    override lateinit var authController: AuthController
    override lateinit var infoController: InfoController

    override fun onCreate() {
        super.onCreate()
        authController = AuthController(
            Retrofit.Builder().baseUrl("http://192.168.0.104:3002/auth/").client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()
            ).addConverterFactory(GsonConverterFactory.create()).build().create(AuthApi::class.java),
            getSharedPreferences("Auth", MODE_PRIVATE)
        )
        infoController = InfoController(
            this,
            Retrofit.Builder().baseUrl("http://192.168.0.104:3002/info/").client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()
            ).addConverterFactory(GsonConverterFactory.create()).build().create(InfoApi::class.java),
            authController
        )
    }

}