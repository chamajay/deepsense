package com.codestack.deepsense.core

import java.net.URL

object Constants {
    //App
    const val TAG = "AppTag"

    //App Version
    const val APP_VER = "1.0.0"

    //Server
    val BASE_URL = URL("http://192.168.1.102:5000")

    //Collection References
    const val USERS = "users"

    //User fields
    const val DISPLAY_NAME = "displayName"
    const val EMAIL = "email"
    const val PHOTO_URL = "photoUrl"
    const val CREATED_AT = "createdAt"

    //Names
    const val SIGN_IN_REQUEST = "signInRequest"
    const val SIGN_UP_REQUEST = "signUpRequest"

    //Buttons
    const val SIGN_IN_WITH_GOOGLE = "Sign in with Google"
    const val SIGN_OUT = "Sign-out"
    const val REVOKE_ACCESS = "Revoke Access"

    //Screens
    const val AUTH_SCREEN = "Authentication"
    const val PROFILE_SCREEN = "Profile"

    //Messages
    const val REVOKE_ACCESS_MESSAGE = "You need to re-authenticate before revoking the access."
}