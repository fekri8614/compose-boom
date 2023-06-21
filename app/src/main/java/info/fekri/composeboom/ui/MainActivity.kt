package info.fekri.composeboom.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import info.fekri.composeboom.di.myModules
import info.fekri.composeboom.ui.feature.entry1.FirstEntryScreen
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.ui.theme.ComposeBoomTheme
import info.fekri.composeboom.util.MyScreens
import org.koin.android.ext.koin.androidContext

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    ComposeBoomTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainAppUi()
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        setContent {
            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModules)
            }) {
                ComposeBoomTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = BackgroundMain
                    ) {
                        MainAppUi()
                    }
                }
            }
        }
    }
}

@Composable
fun MainAppUi() {
    val context = LocalContext.current
    val controller = rememberNavController()
    
    KoinNavHost(navController = controller, startDestination = MyScreens.EntryScreenFirst.route) {


        composable(MyScreens.EntryScreenFirst.route) {
            FirstEntryScreen()
        }

        composable(MyScreens.EntryScreenSecond.route) {
            EntrySecondScreen()
        }

        composable(MyScreens.MainScreen.route) {
            MainScreen()
        }

        composable(MyScreens.ShowBookScreen.route) {
            ShowBookScreen()
        }

        composable(MyScreens.SearchScreen.route) {
            SearchScreen()
        }

    }

}

@Composable
fun SearchScreen() {

}

@Composable
fun ShowBookScreen() {

}

@Composable
fun MainScreen() {

}

@Composable
fun EntrySecondScreen() {

}



