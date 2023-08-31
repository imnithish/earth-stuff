package common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import util.UIErrorType

@Composable
fun ErrorLayout(
    errorType: UIErrorType,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (errorType) {
            UIErrorType.Network -> Text("No internet!", style = MaterialTheme.typography.h5)
            is UIErrorType.Other -> Text(errorType.error, style = MaterialTheme.typography.h5)
            else -> {/* no-op */
            }
        }

        TextButton(onClick) {
            Text(
                "Retry?",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.button,
                textAlign = TextAlign.Center
            )
        }
    }

}