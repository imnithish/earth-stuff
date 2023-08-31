import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import theme.EarthTheme

@Composable
fun App() {
    EarthTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Nav()
        }
    }
}

expect fun getPlatformName(): String