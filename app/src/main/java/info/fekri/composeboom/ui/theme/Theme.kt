package info.fekri.composeboom.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColors = lightColors(
    primary = PrimaryDarkColor,
    secondary = PrimaryDarkColor,
    background = BackgroundMain
)

@Composable
fun ComposeBoomTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = LightColors,
        typography = Typography,
        content = content,
        shapes = Shapes
    )

    val uiController = rememberSystemUiController()
    uiController.setSystemBarsColor(BackgroundMain)

}