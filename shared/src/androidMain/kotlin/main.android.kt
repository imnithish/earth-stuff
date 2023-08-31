import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import dev.imn.earth.common.R

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView() = App()


actual val sailecFontFamily: FontFamily = FontFamily(
    Font(R.font.sailec_regular)
)