package com.codestack.deepsense.navigation

sealed class Screens(val route: String) {
    object Welcome: Screens(route = "welcome_screen")
    object SignUp: Screens(route = "signup_screen")
    object Home: Screens(route = "home_screen")
    object Activity: Screens(route = "activity_screen")
    object Login: Screens(route = "login_screen")
    object AboutUs: Screens(route = "aboutus_screen")

}

