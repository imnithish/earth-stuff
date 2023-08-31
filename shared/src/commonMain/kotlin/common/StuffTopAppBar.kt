package common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun StuffTopAppBar(title: String, onNavigateUp: (() -> Unit)? = null) {
    Column {
        Box(modifier = Modifier.padding(vertical = 16.dp)) {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                title = {
                    Text(
                        title,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                elevation = 0.dp,
                navigationIcon = if (onNavigateUp == null) null else ({
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            painterResource("arrow_back.xml"),
                            "Go back",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.scale(1.5f)
                        )
                    }
                })
            )
        }
        Divider(color = MaterialTheme.colors.primary)
    }
}