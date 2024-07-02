import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dealabs_clone.R


@Composable
fun ConnectionScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Align items to center horizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.photo_profil),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(80.dp)
//                .clip(CircleShape) // Make the image circular
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("LoginScreen") }) {
            Text("CONNEXION / INSCRIPTION")
        }

    }
}

@Preview
@Composable
private fun ConnectionScreenPreview() {
    val navController = rememberNavController()
    ConnectionScreen(navController = navController)
}