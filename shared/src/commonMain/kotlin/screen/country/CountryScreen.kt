package screen.country

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import common.CountryCard
import common.ErrorLayout
import common.StuffButton
import common.StuffCard
import common.StuffTopAppBar
import kotlinx.coroutines.launch
import screen.country.data.CurrentBottomSheetContent
import util.UIErrorType

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun CountryScreen(
    viewModel: CountryViewModel,
    code: String?,
    onBackPress: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.setup(code)
    }

    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

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
            ErrorLayout(uiState.error, viewModel::attemptCountryQuery)
        }

        AnimatedVisibility(uiState.data != null, enter = fadeIn(), exit = fadeOut()) {
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    StuffTopAppBar(
                        title = "${uiState.data?.name}\n${uiState.data?.code}",
                        onNavigateUp = onBackPress
                    )
                },
                sheetElevation = 0.dp,
                sheetPeekHeight = 0.dp,
                sheetShape = RectangleShape,
                sheetBackgroundColor = Color.Transparent,
                sheetContent = {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(),
                        elevation = 0.dp,
                        color = MaterialTheme.colors.primaryVariant,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                start = 32.dp,
                                end = 32.dp,
                                bottom = 32.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(32.dp)
                        ) {
                            when (uiState.currentBottomSheetContent) {
                                CurrentBottomSheetContent.STATES -> {
                                    stickyHeader {
                                        Text(
                                            modifier = Modifier.background(MaterialTheme.colors.primaryVariant)
                                                .padding(top = 32.dp).fillMaxSize(),
                                            text = "States",
                                            style = MaterialTheme.typography.h4,
                                            textDecoration = TextDecoration.Underline,
                                            color = MaterialTheme.colors.primary
                                        )
                                    }
                                    uiState.data?.states?.forEach {
                                        item {
                                            Column {
                                                Text(
                                                    "${it.name}\n${it.code}",
                                                    style = MaterialTheme.typography.h6,
                                                    textAlign = TextAlign.Start,
                                                    color = MaterialTheme.colors.primary
                                                )
                                                Spacer(Modifier.height(16.dp))
                                                Divider(color = MaterialTheme.colors.primary)
                                            }
                                        }
                                    }
                                }

                                CurrentBottomSheetContent.LANGUAGES -> {
                                    stickyHeader {
                                        Text(
                                            modifier = Modifier.background(MaterialTheme.colors.primaryVariant)
                                                .padding(top = 32.dp).fillMaxSize(),
                                            text = "Languages",
                                            style = MaterialTheme.typography.h4,
                                            textDecoration = TextDecoration.Underline,
                                            color = MaterialTheme.colors.primary
                                        )
                                    }
                                    uiState.data?.languages?.forEach {
                                        item {
                                            Column {
                                                Text(
                                                    "${it.name} - ${it.native}",
                                                    style = MaterialTheme.typography.h6,
                                                    textAlign = TextAlign.Start,
                                                    color = MaterialTheme.colors.primary
                                                )
                                                Spacer(Modifier.height(16.dp))
                                                Text(
                                                    "Code: ${it.code}, Rtl: ${it.rtl}",
                                                    style = MaterialTheme.typography.h6,
                                                    textAlign = TextAlign.Start,
                                                    color = MaterialTheme.colors.primary
                                                )
                                                Spacer(Modifier.height(16.dp))
                                                Divider(color = MaterialTheme.colors.primary)
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            ) {
                Scaffold { paddingValues ->
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.fillMaxSize().padding(paddingValues),
                        columns = StaggeredGridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        verticalItemSpacing = 16.dp,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            StuffCard {
                                Text(
                                    "${uiState.data?.emoji}",
                                    style = MaterialTheme.typography.h4,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        item {
                            StuffCard {
                                Text(
                                    "Continent",
                                    style = MaterialTheme.typography.body2,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    "${uiState.data?.continent?.name}\n${uiState.data?.continent?.code}",
                                    style = MaterialTheme.typography.h5,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        item {
                            StuffCard {
                                Text(
                                    "Currency",
                                    style = MaterialTheme.typography.body2,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    "${uiState.data?.currency}",
                                    style = MaterialTheme.typography.h6,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        item {
                            StuffCard {
                                Text(
                                    "Native",
                                    style = MaterialTheme.typography.body2,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    "${uiState.data?.native}",
                                    style = MaterialTheme.typography.h4,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        item {
                            StuffCard {
                                Text(
                                    "Phone",
                                    style = MaterialTheme.typography.body2,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    "${uiState.data?.phone}",
                                    style = MaterialTheme.typography.h4,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        if (!uiState.data?.states.isNullOrEmpty())
                            item {
                                StuffButton(
                                    "States",
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        backgroundColor = MaterialTheme.colors.primaryVariant
                                    )
                                ) {
                                    scope.launch {
                                        viewModel.setCurrentBottomSheet(CurrentBottomSheetContent.STATES)
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            }

                        if (!uiState.data?.languages.isNullOrEmpty())
                            item {
                                StuffButton(
                                    "Languages", colors = ButtonDefaults.outlinedButtonColors(
                                        backgroundColor = MaterialTheme.colors.primaryVariant
                                    )
                                ) {
                                    scope.launch {
                                        viewModel.setCurrentBottomSheet(CurrentBottomSheetContent.LANGUAGES)
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            }
                    }
                }
            }

        }
    }

}