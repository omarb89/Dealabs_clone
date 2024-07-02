import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.node.CanFocusChecker.start
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PromoCode() {
    var promoCode by remember { mutableStateOf(TextFieldValue()) }
    var promoLink by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Poster un nouveau code promo",
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp),
            fontSize = 20.sp
        )

        Text(
            text = "Partagez un code promo avec des millions de personnes",
            fontSize = 16.sp,
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
        )
        BasicTextField(
            value = promoCode,
            onValueChange = { promoCode = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(8.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (promoCode.text.isEmpty()) {
                    Text("Code promo")
                }
                innerTextField()
            }
        )
        BasicTextField(
            value = promoLink,
            onValueChange = { promoLink = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(8.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (promoLink.text.isEmpty()) {
                    Text(text = "Lien du code promo")
                }
                innerTextField()
            }
        )
        Button(
            onClick = { /* TODO: Handle continue button click */ },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "CONTINUER")
        }
        TextButton(
            onClick = { /* TODO: Handle no code or link button click */ }
        ) {
            Text(text = "Je n'ai ni code ni lien")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PromoCode()
}
