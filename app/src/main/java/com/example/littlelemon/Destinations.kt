package com.example.littlelemon

interface Destinations {
    val route:String
}

object Home : Destinations {
    override val route = "HomeScreen"
}

object Profile : Destinations {
    override val route = "ProfileScreen"
}

object Onboarding : Destinations {
    override val route = "OnboardingScreen"
}