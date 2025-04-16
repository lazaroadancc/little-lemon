package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        ) {
            // Imagen del logo centrado
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.4f) // 50% del ancho disponible
                    .aspectRatio(1f)    // Mantiene proporción 1:1 (cuadrado)
            )

            // Imagen de perfil a la derecha
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .height(50.dp)
                    .align(Alignment.CenterEnd)
                    .clickable {
                        navController.navigate(Profile.route)
                    }
            )
        }
        Column(
            modifier = Modifier
                .background(Color(0xFF495E57))
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
        ) {

            Text(
                text = stringResource(id = R.string.title),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF4CE14)
            )
            Text(
                text = stringResource(id = R.string.location),
                fontSize = 24.sp,
                color = Color(0xFFEDEFEE)
            )
            Row(
                modifier = Modifier
                    .padding(top = 18.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.description),
                    color = Color(0xFFEDEFEE),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 28.dp)
                        .fillMaxWidth(0.6f)
                )
                Image(
                    painter = painterResource(id = R.drawable.upperpanelimage),
                    contentDescription = "Upper Panel Image",
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                )
            }
            SearchBar(
                query = searchQuery,
                onQueryChanged = { searchQuery = it },
                onSearch = {
                    // Aquí haces lo que necesites cuando se presiona "Buscar"
                    println("Buscando: $searchQuery")
                }
            )
        }
        Column {
            Text(
                text = stringResource(id = R.string.order_for_delivery),
                color = Color(0xFF000000),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 28.dp)
            )
            CategoryButtonsRow()
        }
        LowerPanel()
    }
}

@Composable
private fun LowerPanel() {
    Column {
        WeeklySpecialCard()
        MenuDish()
    }
}


@Composable
fun WeeklySpecialCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Weekly Special",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}


@Composable
fun MenuDish() {
    LazyColumn {
        items(Dishes) { Dish ->
            MenuDish(Dish)
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit,
    placeholder: String = "Search..."
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = query,
        onValueChange = { onQueryChanged(it) },
        label = { Text(placeholder) },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Ícono de búsqueda")
        },
        singleLine = true, // 👈 esto lo hace de una sola línea
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search // 👈 cambia el botón "Enter" por "Buscar"
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide() // 👈 oculta el teclado
                onSearch() // 👈 ejecuta tu función de búsqueda
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White)
    )
}
@Composable
fun CategoryButtonsRow() {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CategoryButton(text = "Starters")
        CategoryButton(text = "Mains")
        CategoryButton(text = "Desserts")
        CategoryButton(text = "Drinks")
    }
}

@Composable
fun CategoryButton(text: String) {
    Button(
        onClick = { /* Acción para cada categoría */ },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .padding(horizontal = 2.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun LowerPanelPreview() {
    LowerPanel()
}

