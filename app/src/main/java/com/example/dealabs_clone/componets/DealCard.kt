import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun DealCard(
    titre: String,
    prix: String,
    prixHabituel: String,
    photoUrl: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
//        elevation = c,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(photoUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = titre, style = MaterialTheme.typography.headlineLarge)
            Text(text = "Prix : $prix €", style = MaterialTheme.typography.headlineLarge)
            Text(text = "Prix habituel : $prixHabituel €", style = MaterialTheme.typography.headlineLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DealCardPreview() {
    DealCard(
        titre = "Nom du deal",
        prix = "19.99",
        prixHabituel = "29.99",
        photoUrl = "https://url_de_votre_image.jpg"
    )
}
