import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dealabs_clone.LoginScreen

@Composable
fun NavGraph(navController: NavHostController, startDestination: String = "header") {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("header") { Header(navController) }
        composable("urlDeals") { UrlDeals(navController = navController)}
        composable("promoCode") { PromoCode() }
        composable("connectionScreen") { ConnectionScreen(navController = navController) }
        composable("loginScreen") {
            LoginScreen(
                onFacebookLogin = { /*TODO: handle Facebook login*/ },
                onGoogleLogin = { /*TODO: handle Google login*/ },
                onAppleLogin = { /*TODO: handle Apple login*/ },
                onEmailLogin = { /*TODO: handle Email login*/ }
            )
        }
        composable("formulaireDeal") { FormulaireDeal(navController) }
        composable("photoSelection") { PhotoSelection(navController = navController) }

    }
}
