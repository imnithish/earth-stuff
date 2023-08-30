import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun App() {
    MaterialTheme(
        colors = lightColors()
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Nav()
        }
    }
}

expect fun getPlatformName(): String