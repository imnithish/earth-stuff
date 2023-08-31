package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EarthTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = if (isSystemInDarkTheme())
            darkColors(
                primary = Color.White,
                surface = Color.Black,
                onSurface = Color.White,
                onPrimary = Color.Black,
                error= Color.LightGray
            )
        else lightColors(
            primary = Color.Black,
            surface = Color.White,
            onSurface = Color.Black,
            onPrimary = Color.White,
            error= Color.Blue
        ),
        typography = Typography,
        shapes = Shapes(
            small = RoundedCornerShape(8.dp),
            medium = RoundedCornerShape(12.dp),
            large = RoundedCornerShape(16.dp)
        )
    ) {
        content()
    }
}