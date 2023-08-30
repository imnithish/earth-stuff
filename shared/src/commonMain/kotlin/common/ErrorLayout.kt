package common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
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
            UIErrorType.Network -> Text(
                "No internet!",
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            is UIErrorType.Other -> Text(
                errorType.error,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            else -> {/* no-op */
            }
        }

        TextButton(onClick) {
            Text(
                "Retry?",
                color = Color.Blue,
                textAlign = TextAlign.Center
            )
        }
    }

}