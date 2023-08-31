package common

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun StuffButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        onClick = onClick
    ) {
        Text(
            text,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}