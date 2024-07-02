package com.example.dealabs_clone

import Footer
import NavGraph
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.dealabs_clone.ui.theme.Dealabs_cloneTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects

data class Deal(
    val titre: String = "",
    val prix: String = "",
    val prixHabituel: String = "",
    val photoUrl: String = ""
)

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account)
        } catch (e: ApiException) {
            Log.w("MainActivity", "Google sign in failed", e)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setContent {
            Dealabs_cloneTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { Footer(navController = navController) }
                ) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        NavGraph(navController = navController)
                        if (currentRoute != "formulaireDeal" && currentRoute != "photoSelection") {
                            DealsTabBar()
                            DealList()
                        }
                    }
                }
            }
        }
    }

    fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("MainActivity", "signInWithCredential:success")
                } else {
                    Log.w("MainActivity", "signInWithCredential:failure", task.exception)
                }
            }
    }
}

@Composable
fun DealList() {
    val db = FirebaseFirestore.getInstance()
    val deals = remember { mutableStateListOf<Deal>() }

    LaunchedEffect(Unit) {
        db.collection("deals").get().addOnSuccessListener { result ->
            deals.clear()
            deals.addAll(result.toObjects())
        }
    }

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(deals) { deal ->
            DealCard(
                titre = deal.titre,
                prix = deal.prix,
                prixHabituel = deal.prixHabituel,
                photoUrl = deal.photoUrl
            )
        }
    }
}

@Composable
fun DealCard(titre: String, prix: String, prixHabituel: String, photoUrl: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = titre, style = MaterialTheme.typography.labelLarge)
            Text(text = "Prix: $prix €", style = MaterialTheme.typography.bodyMedium, color = Color.Red)
            Text(text = "Prix habituel: $prixHabituel €", style = MaterialTheme.typography.bodyMedium)
            if (photoUrl.isNotEmpty()) {
                Image(
                    painter = rememberImagePainter(photoUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Dealabs_cloneTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { Footer(navController = navController) }
        ) { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavGraph(navController = navController)
                DealList()
            }
        }
    }
}
