import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Footer(navController: NavHostController) {
//    val bottomPadding = with(LocalDensity.current) { 16.dp.toPx().toInt() } // Espacement en bas de l'écran

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White) // Couleur de fond du footer
//            .padding(bottom = bottomPadding.dp) // Couleur de fond du footer
    ) {
        Spacer(modifier = Modifier.height(8.dp)) // Espace pour le séparer du contenu au-dessus

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { navController.navigate("header") }) { // Navigate to HomeScreen
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Accueil"
                )
            }

//            BadgedBox(
//                badge = {
//                    Badge {
//                        Text("5", style = TextStyle(fontSize = 12.sp))
//                    }
//                }
//            ) {
            IconButton(onClick = { /* Action lors du clic */ }) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications"
                )
            }
//            }

            IconButton(onClick = { /* Action lors du clic */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Recherche"
                )
            }

            IconButton(onClick = { navController.navigate("connectionScreen") }) { // Navigate to ConnectionScreen
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Mail"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFooter() {
    val navController = rememberNavController()
    Footer(navController = navController)
}
