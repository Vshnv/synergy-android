package io.github.synergy

import io.github.synergy.controller.AuthController
import io.github.synergy.controller.InfoController

interface AppStateProvider {
    val authController: AuthController
    val infoController: InfoController
}