package info.fekri.composeboom.util

sealed class MyScreens(val route: String) {
    object SplashScreen: MyScreens("SplashScreen")
    object EntryScreenFirst : MyScreens("entryScreenFirst")
    object EntryScreenSecond : MyScreens("entryScreenSecond")
    object MainScreen : MyScreens("mainScreen")
    object ShowBookScreen: MyScreens("showBookScreen")
    object SearchScreen: MyScreens("searchScreen")
    object AboutUsScreen: MyScreens("aboutUsScreen")
    object ProfileScreen: MyScreens("profileScreen")
    object ShowPdfScreen: MyScreens("showPdfScreen")

    object VoiceLibScreen: MyScreens("voiceLibScreen")
    object VideoLibScreen: MyScreens("videoLibScreen")
    object PhotoLibScreen: MyScreens("photoLibScreen")
}
