package com.example.littlelemon


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mostrarAlerta by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Encabezado con logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(vertical = 16.dp)
        )

        // Texto introductorio
        Column(

            modifier = Modifier
                .background(Color(0xFF495E57))
                .height(150.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Let's get to know you",
                fontSize = 24.sp,
                color = Color(0xFFFFFFFF))
        }

        Text(text = "Personal Information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            modifier = Modifier.padding(top = 32.dp, bottom = 32.dp, start = 16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
        ){
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(64.dp))

            Button(
                onClick = {
                    if (email.isNotEmpty()) {
                        navController.navigate(Home.route) {
                            popUpTo(Onboarding.route) { inclusive = true }
                        }
                    }else {
                        mostrarAlerta = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14))
            ) {
                Text("Register",
                    color = Color.Black)
            }
            if (mostrarAlerta) {
                AlertDialog(
                    onDismissRequest = { mostrarAlerta = false },
                    title = { Text("Missing data") },
                    text = { Text("Complete email field") },
                    confirmButton = {
                        Button(
                            onClick = {
                                // acción confirmada
                                mostrarAlerta = false
                            }
                        ) {
                            Text("Accept")
                        }
                    }
                )
            }
        }

    }
}