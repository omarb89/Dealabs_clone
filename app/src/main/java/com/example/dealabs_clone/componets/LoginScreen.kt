package com.example.dealabs_clone

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dealabs_clone.ui.theme.Dealabs_cloneTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account)
        } catch (e: ApiException) {
            Log.w(TAG, "Google sign in failed", e)
            // Handle Google Sign In failure
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setContent {
            Dealabs_cloneTheme {

                LoginScreen(
                    onFacebookLogin = { /* Handle Facebook login */ },
                    onGoogleLogin = { signInWithGoogle() },
                    onAppleLogin = { /* Handle Apple login */ },
                    onEmailLogin = { /* Handle Email login */ }
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {

        }
    }

    private fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    // Handle successful login
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    // Handle login failure
                }
            }
    }
}

@Composable
fun LoginScreen(
    onFacebookLogin: () -> Unit,
    onGoogleLogin: () -> Unit,
    onAppleLogin: () -> Unit,
    onEmailLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenue! Créez un compte pour rejoindre la communauté et profiter des meilleurs deals")
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(
            text = "FACEBOOK",
            icon = R.drawable.ic_facebook,
            onClick = onFacebookLogin
        )
        Spacer(modifier = Modifier.height(8.dp))
        LoginButton(
            text = "GOOGLE",
            icon = R.drawable.ic_google,
            onClick = onGoogleLogin
        )
        Spacer(modifier = Modifier.height(8.dp))
        LoginButton(
            text = "APPLE",
            icon = R.drawable.ic_apple,
            onClick = onAppleLogin
        )
        Spacer(modifier = Modifier.height(8.dp))
        LoginButton(
            text = "E-MAIL",
            icon = R.drawable.ic_email,
            onClick = onEmailLogin
        )
    }
}

@Composable
fun LoginButton(
    text: String,
    icon: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "$text Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    Dealabs_cloneTheme {
        LoginScreen(
            onFacebookLogin = { /* Preview Action */ },
            onGoogleLogin = { /* Preview Action */ },
            onAppleLogin = { /* Preview Action */ },
            onEmailLogin = { /* Preview Action */ }
        )
    }
}
