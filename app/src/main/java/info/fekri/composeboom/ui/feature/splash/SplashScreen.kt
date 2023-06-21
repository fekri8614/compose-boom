package info.fekri.composeboom.ui.feature.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dev.burnoo.cokoin.navigation.getNavController
import info.fekri.composeboom.ui.theme.ComposeBoomTheme

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    ComposeBoomTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SplashScreen()
        }
    }
}

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    val navigation = getNavController()



}