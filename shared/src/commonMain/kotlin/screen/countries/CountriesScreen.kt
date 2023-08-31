package screen.countries

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import common.CountryCard
import common.ErrorLayout
import common.StuffTopAppBar
import util.UIErrorType

@Composable
fun CountriesScreen(
    code: String?,
    viewModel: CountriesViewModel,
    onCountryClick: (String) -> Unit,
    onBackPress: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.setup(code)
    }

    val uiState by viewModel.uiState.collectAsState()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedVisibility(
            uiState.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(
            uiState.error != UIErrorType.Nothing,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ErrorLayout(uiState.error, viewModel::attemptContinentQuery)
        }

        AnimatedVisibility(uiState.countries.isNotEmpty(), enter = fadeIn(), exit = fadeOut()) {
            Scaffold(
                topBar = {
                    StuffTopAppBar(
                        title = "${uiState.continentCodeAndName?.second}\n${uiState.continentCodeAndName?.first}",
                        onNavigateUp = onBackPress
                    )
                }
            ) { paddingValues ->
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    verticalItemSpacing = 16.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    uiState.countries.forEach {
                        item {
                            CountryCard(code = it.code, name = it.name, emoji = it.emoji) {
                                onCountryClick(it.code)
                            }
                        }
                    }
                }
            }
        }
    }
}