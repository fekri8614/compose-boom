package info.fekri.composeboom.util

sealed class MyScreens(val route: String) {
    object EntryScreenFirst : MyScreens("entryScreenFirst")
    object EntryScreenSecond : MyScreens("entryScreenSecond")
    object MainScreen : MyScreens("mainScreen")
    object ShowBookScreen: MyScreens("showBookScreen")
    object SearchScreen: MyScreens("searchScreen")
    object SplashScreen: MyScreens("splashScreen")
}
