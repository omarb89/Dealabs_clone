import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dealabs_clone.R

@Composable
fun Header(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF333333))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left side: BurgerMenu
        BurgerMenu()

        // Right side: AddDeals
        AddDeals(navController)
    }
}

@Composable
fun BurgerMenu() {
    val menuExpanded = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(8.dp)) {
        IconButton(
            onClick = { menuExpanded.value = !menuExpanded.value },
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Ouvrir le Menu",
                tint = Color.White
            )
        }

        if (menuExpanded.value) {
            Popup(
                alignment = Alignment.BottomCenter,
                onDismissRequest = { menuExpanded.value = false },

                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.7f)
                            .background(color = Color.White)
                            .padding(16.dp)
                    ) {
                        Column {
                            Text(
                                "DEALABS CLONE",
                                color = Color.Black
                            )
                            TextButton(onClick = { }) {
                                Text("Nouveautés", color = Color.Black)
                            }
                            TextButton(onClick = { }) {
                                Text("Catégories", color = Color.Black)
                            }
                            TextButton(onClick = { }) {
                                Text("Discussion", color = Color.Black)
                            }
                            TextButton(onClick = { }) {
                                Text("À Propos", color = Color.Black)
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun AddDeals(navController: NavController) {
    val menuExpanded = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(8.dp)) {
        IconButton(
            onClick = { menuExpanded.value = !menuExpanded.value },
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Ouvrir le Menu",
                tint = Color.White
            )
        }

        if (menuExpanded.value) {
            // Popup modifié pour s'afficher en bas de l'écran
            Popup(
                alignment = Alignment.BottomCenter,
                onDismissRequest = { menuExpanded.value = false },
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(color = Color.White)
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                "Que souhaitez-vous faire?",
                                color = Color.Black
                            )

                            TextButton(onClick = { navController.navigate("UrlDeals") }) {
                                Text("Poster un bon plan", color = Color.Black)
                            }
                            TextButton(onClick = { navController.navigate("PromoCode")}) {
                                Text("Poster un code promo", color = Color.Black)
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    val navController = rememberNavController()
    Header(navController = navController)
}