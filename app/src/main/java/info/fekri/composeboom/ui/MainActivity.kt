package info.fekri.composeboom.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import info.fekri.composeboom.di.myModules
import info.fekri.composeboom.ui.feature.aboutUs.AboutUsScreen
import info.fekri.composeboom.ui.feature.entry1.FirstEntryScreen
import info.fekri.composeboom.ui.feature.entry2.EntrySecondScreen
import info.fekri.composeboom.ui.feature.main.MainScreen
import info.fekri.composeboom.ui.feature.pdf.ShowPdfScreen
import info.fekri.composeboom.ui.feature.profile.ProfileScreen
import info.fekri.composeboom.ui.feature.search.SearchScreen
import info.fekri.composeboom.ui.feature.showbook.ShowBookScreen
import info.fekri.composeboom.ui.feature.splash.SplashScreen
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.ui.theme.ComposeBoomTheme
import info.fekri.composeboom.util.IS_USER_FIRST_TIME
import info.fekri.composeboom.util.KEY_SHOW_BOOK
import info.fekri.composeboom.util.KEY_SHOW_PDF
import info.fekri.composeboom.util.MyScreens
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        val sharedPreferences = getSharedPreferences("my_fist_t_checker_sh", Context.MODE_PRIVATE)
        val isFirstTime = sharedPreferences.getBoolean(IS_USER_FIRST_TIME, true)
        if (isFirstTime) {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(IS_USER_FIRST_TIME, false)
            editor.apply()
        }
        setContent {
            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModules)
            }) {
                ComposeBoomTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(), color = BackgroundMain
                    ) {
                        MainAppUi(isFirstTime)
                    }
                }
            }
        }
    }
}

@Composable
fun MainAppUi(isFirstTime: Boolean) {
    val controller = rememberNavController()

    KoinNavHost(navController = controller, startDestination = MyScreens.EntryScreenFirst.route) {

        composable(route = MyScreens.EntryScreenFirst.route) {
            FirstEntryScreen()
        }

        composable(MyScreens.EntryScreenSecond.route) {
            EntrySecondScreen()
        }

        composable(MyScreens.MainScreen.route) {
            MainScreen()
        }

        composable(
            MyScreens.ShowBookScreen.route + "/{$KEY_SHOW_BOOK}",
            arguments = listOf(navArgument(KEY_SHOW_BOOK) {
                type = NavType.StringType
            })
        ) {
            ShowBookScreen(it.arguments!!.getString(KEY_SHOW_BOOK, "null"))
        }

        composable(
            MyScreens.ShowPdfScreen.route + "/{$KEY_SHOW_PDF}",
            arguments = listOf(navArgument(KEY_SHOW_PDF) {
                type = NavType.StringType
            })
        ) {
            ShowPdfScreen(it.arguments!!.getString(KEY_SHOW_PDF, "null"))
        }

        composable(MyScreens.SplashScreen.route) {
            SplashScreen(isFirstTime)
        }

        composable(MyScreens.SearchScreen.route) {
            SearchScreen()
        }

        composable(MyScreens.AboutUsScreen.route) {
            AboutUsScreen()
        }

        composable(MyScreens.ProfileScreen.route) {
            ProfileScreen()
        }

        composable(MyScreens.VoiceLibScreen.route) {
            VoiceLibScreen()
        }

        composable(MyScreens.VideoLibScreen.route) {
            VideoLibScreen()
        }

        composable(MyScreens.PhotoLibScreen.route) {
            PhotoLibScreen()
        }

    }

}


@Composable
fun PhotoLibScreen() {
    NoDataYetPage()
}

@Composable
fun VideoLibScreen() {
    NoDataYetPage()
}

@Composable
fun VoiceLibScreen() {
    NoDataYetPage()
}

@Composable
fun NoDataYetPage() {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(BackgroundMain)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "This item is not added yet.")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "We are working on it")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Thank you for understanding :-*")
        }
    }
}
