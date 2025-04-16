package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                val estaLogueado = remember {
                    mutableStateOf(false)
                }
                LaunchedEffect(Unit) {
                    val sharedPref = context.getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
                    estaLogueado.value = sharedPref.getBoolean("logueado", false)
                    println("esta logueado: "+estaLogueado.value)
                    // Navegamos según el estado
                    if (estaLogueado.value) {
                        navController.navigate(Home.route) {
                            popUpTo(Onboarding.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Onboarding.route) {
                            popUpTo(Home.route) { inclusive = true }
                        }
                    }
                }

                NavHost(navController = navController, startDestination = Onboarding.route){
                    composable(Home.route){
                        HomeScreen(navController)
                    }
                    composable(Onboarding.route){
                        OnboardingScreen(navController)
                    }
                    composable(Profile.route){
                        ProfileScreen(navController)
                    }
                }
            }
        }
    }
}