import androidx.compose.runtime.Composable
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import screen.continents.ContinentsScreen
import screen.continents.ContinentsViewModel
import screen.countries.CountriesScreen
import screen.countries.CountriesViewModel
import screen.country.CountryScreen
import screen.country.CountryViewModel

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
            val vm = koinViewModel(CountriesViewModel::class)
            val code: String? = backStackEntry.path<String>("id")
            CountriesScreen(
                code = code,
                viewModel = vm,
                onCountryClick = {
                    navigator.navigate("/country/$it")
                },
                onBackPress = {
                    navigator.goBack()
                }
            )
        }

        scene(
            route = "/country/{code}?",
            navTransition = NavTransition(),
        ) { backStackEntry ->
            val vm = koinViewModel(CountryViewModel::class)
            val code: String? = backStackEntry.path<String>("code")
            CountryScreen(
                code = code,
                viewModel = vm,
                onBackPress = {
                    navigator.goBack()
                }
            )
        }
    }
}