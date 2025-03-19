


package banquemisr.presentation.navigation.screen

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable


// ✅ List Screen (No Arguments → Use `object`)
@Serializable
object ListScreen

// ✅ Details Screen (Takes movieId Argument → Use `data class`)
@Serializable
data class DetailsScreen(val movieId: Int)