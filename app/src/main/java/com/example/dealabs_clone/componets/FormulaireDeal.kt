import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

data class FormulaireDealData(
    var titre: String = "",
    var prix: String = "0.00",
    var prixHabituel: String = "0.00",
    var codePromo: String = "CODE10",
    var enLigne: Boolean = false,
    var livraisonGratuite: Boolean = false,
    var fraisDePort: String = "0.00",
    var selectedCountry: String = "Aucun"
)

@Composable
fun FormulaireDeal(navController: NavHostController, dealData: FormulaireDealData = FormulaireDealData()) {
    val db = FirebaseFirestore.getInstance()

    var titre by remember { mutableStateOf(TextFieldValue(dealData.titre)) }
    var prix by remember { mutableStateOf(TextFieldValue(dealData.prix)) }
    var prixHabituel by remember { mutableStateOf(TextFieldValue(dealData.prixHabituel)) }
    var codePromo by remember { mutableStateOf(TextFieldValue(dealData.codePromo)) }
    var enLigne by remember { mutableStateOf(dealData.enLigne) }
    var livraisonGratuite by remember { mutableStateOf(dealData.livraisonGratuite) }
    var fraisDePort by remember { mutableStateOf(TextFieldValue(dealData.fraisDePort)) }
    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(dealData.selectedCountry) }

    val countries = remember {
        Locale.getISOCountries().map { Locale("", it).getDisplayCountry(Locale.getDefault()) }
            .toList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Poster un nouveau deal",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = titre,
            onValueChange = { titre = it },
            label = { Text("Titre (obligatoire)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(text = "Détails du prix", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))

        OutlinedTextField(
            value = prix,
            onValueChange = { prix = it },
            label = { Text("Prix (€)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = prixHabituel,
            onValueChange = { prixHabituel = it },
            label = { Text("Prix habituel (€)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = codePromo,
            onValueChange = { codePromo = it },
            label = { Text("Code promo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(text = "Disponibilité", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Button(
                onClick = { enLigne = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (enLigne) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text("En ligne")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { enLigne = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!enLigne) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text("En magasin")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Livraison gratuite", fontSize = 18.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = livraisonGratuite, onCheckedChange = { livraisonGratuite = it })
        }

        if (!livraisonGratuite) {
            OutlinedTextField(
                value = fraisDePort,
                onValueChange = { fraisDePort = it },
                label = { Text("Frais de port (€)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        Text(text = "Pays d'expédition", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))
        Box(modifier = Modifier.padding(bottom = 16.dp)) {
            Button(onClick = { expanded = true }) {
                Text(selectedCountry)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text(text = country) },
                        onClick = {
                            selectedCountry = country
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                dealData.titre = titre.text
                dealData.prix = prix.text
                dealData.prixHabituel = prixHabituel.text
                dealData.codePromo = codePromo.text
                dealData.enLigne = enLigne
                dealData.livraisonGratuite = livraisonGratuite
                dealData.fraisDePort = fraisDePort.text
                dealData.selectedCountry = selectedCountry

                navController.navigate("photoSelection")
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Suivant")
        }
    }
}

@Preview
@Composable
private fun FormulaireDealPreview() {
    val navController = rememberNavController()
    FormulaireDeal(navController = navController)
}
