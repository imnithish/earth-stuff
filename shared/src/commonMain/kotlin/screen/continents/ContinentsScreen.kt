package screen.continents

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.koin.koinViewModel

@Composable
fun ContinentsScreen() {
    val viewModel = koinViewModel<ContinentsViewModel>()

}