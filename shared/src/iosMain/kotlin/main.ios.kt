import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Typeface
import moe.tlaster.precompose.PreComposeApplication
import org.jetbrains.skia.FontStyle
import org.jetbrains.skia.Typeface

actual fun getPlatformName(): String = "iOS"

fun MainViewController() = PreComposeApplication { App() }

private fun loadCustomFont(name: String): Typeface {
    return Typeface.makeFromName(name, FontStyle.NORMAL)
}

actual val sailecFontFamily: FontFamily = FontFamily(
    Typeface(loadCustomFont("sailec_regular"))
)