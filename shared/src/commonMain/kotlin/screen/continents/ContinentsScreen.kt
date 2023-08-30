package screen.continents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import common.ErrorLayout
import util.UIErrorType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContinentsScreen(viewModel: ContinentsViewModel, onNavigate: (String) -> Unit) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.attemptContinentsQuery()
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        AnimatedVisibility(uiState.isLoading) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(uiState.error != UIErrorType.Nothing) {
            ErrorLayout(uiState.error, viewModel::attemptContinentsQuery)
        }

        AnimatedVisibility(uiState.continents.isNotEmpty()) {
            FlowRow(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            ) {

                uiState.continents.forEach {
                    OutlinedButton(
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                            onNavigate(it.code)
                        }
                    ) {
                        Text(
                            "${it.name}\n${it.code}",
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }


}