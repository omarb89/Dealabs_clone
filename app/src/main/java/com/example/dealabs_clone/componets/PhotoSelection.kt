import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

@Composable
fun PhotoSelection(navController: NavHostController, dealData: FormulaireDealData = FormulaireDealData()) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    val db = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Sélectionner une photo", modifier = Modifier.padding(bottom = 16.dp))

        Button(onClick = { imagePickerLauncher.launch("image/*") }) {
            Text(text = "Choisir une image")
        }

        selectedImageUri?.let { uri ->
            Image(
                painter = rememberImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            selectedImageUri?.let { uri ->
                val storageRef = storage.reference
                val photoRef = storageRef.child("deals/${UUID.randomUUID()}.jpg")
                val uploadTask = photoRef.putFile(uri)

                uploadTask.addOnSuccessListener {
                    photoRef.downloadUrl.addOnSuccessListener { downloadUri ->
                        val deal = mapOf(
                            "titre" to dealData.titre,
                            "prix" to dealData.prix,
                            "prixHabituel" to dealData.prixHabituel,
                            "codePromo" to dealData.codePromo,
                            "enLigne" to dealData.enLigne,
                            "livraisonGratuite" to dealData.livraisonGratuite,
                            "fraisDePort" to dealData.fraisDePort,
                            "selectedCountry" to dealData.selectedCountry,
                            "photoUrl" to downloadUri.toString()
                        )
                        db.collection("deals").add(deal)
                            .addOnSuccessListener {
                                navController.navigate("main")
                            }
                    }
                }
            }
        }) {
            Text("Enregistrer le deal")
        }
    }
}

@Preview
@Composable
private fun PhotoSelectionPreview() {
    val navController = rememberNavController()
    PhotoSelection(navController = navController)
}
