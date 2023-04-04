package com.codestack.deepsense.navigation

sealed class Screens(val route: String) {
    object Welcome: Screens(route = "welcome_screen")
    object SignUp: Screens(route = "signup_screen")
    object Home: Screens(route = "home_screen")
    object Activity: Screens(route = "activity_screen")
    object Signin: Screens(route = "sign_in_screen")
    object AboutUs: Screens(route = "aboutus_screen")
    object ContactUs: Screens(route = "contactus_screen")
    object Settings: Screens(route = "settings_screen")
    object Main: Screens(route = "main_screen")
    object Accessibility: Screens(route = "accessibility_screen")
    object EmergencyContact: Screens(route = "emergency_screen")
}

