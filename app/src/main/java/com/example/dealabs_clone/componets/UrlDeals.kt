import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun UrlDeals(navController: NavHostController) {
    var lienDeal =
        remember { mutableStateOf(TextFieldValue("https://www.example.com/superdeal...")) } // État du champ de texte

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Poster un nouveau deal",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 50.dp, end = 16.dp, bottom = 16.dp)

        )

        Text(
            text = "Partagez un bon plan avec des millions de personnes",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Veuillez donner le lien de la page où la communauté pourra obtenir des renseignements sur votre bon plan.",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Lien du deal") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            )
        )

//        OutlinedTextField(
//            value = lienDeal,
//            onValueChange = { },
//            label = Text("Lien du deal"),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Uri,
//                imeAction = ImeAction.Done
//            ),
//            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
//            keyboardActions = KeyboardActions(
//                onDone = {
//
//                }
//            )
//        )

        /*Button(
            onClick = {
                // Action à effectuer lors du clic sur "Continuer"
                // Par exemple, vérifier le lien ou passer à l'étape suivante
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("CONTINUER")
        }*/

        TextButton(onClick = {
            navController.navigate("formulaireDeal") // Navigue vers la page FormulaireDeal
        }) {
            Text("Je n'ai pas de lien")
        }
    }
}

@Preview
@Composable
private fun UrlDealsPreview() {
    val navController = rememberNavController()
    UrlDeals(navController = navController)
}
