package info.fekri.composeboom.util

sealed class MyScreens(val route: String) {
    object EntryScreenFirst : MyScreens("entryScreenFirst")
    object EntryScreenSecond : MyScreens("entryScreenSecond")
    object MainScreen : MyScreens("mainScreen")
    object ShowBookScreen: MyScreens("showBookScreen")
    object SplashScreen: MyScreens("splashScreen")
    object SearchScreen: MyScreens("searchScreen")
    object MoreScreen: MyScreens("moreScreen")
}

val screens = listOf(
    MyScreens.MoreScreen,
    MyScreens.SearchScreen
)
