import androidx.compose.runtime.Composable
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import screen.continents.ContinentsScreen
import screen.continents.ContinentsViewModel
import screen.countries.CountriesScreen

@Composable
fun Nav() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = "/continents",
    ) {
        scene(
            route = "/continents",
            navTransition = NavTransition(),
        ) {
            val vm = koinViewModel(ContinentsViewModel::class)
            ContinentsScreen(vm) {
                navigator.navigate("/countries/$it")
            }
        }

        scene(route = "/countries/{id}?") { backStackEntry ->
            val code: String? = backStackEntry.path<String>("id")
            CountriesScreen(code)
        }
    }
}