package info.fekri.composeboom.ui.feature.splash

import android.os.Handler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.R
import info.fekri.composeboom.util.MyScreens

@Composable
fun SplashScreen(isFirstTime: Boolean) {
    val context = LocalContext.current
    val navigation = getNavController()
    val viewModel = getNavViewModel<SplashViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        MyAnimaShower(R.raw.welcome_owl)

        Text(
            text = "Welcome to Boom!",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif
        )

        Handler().postDelayed({
            if (isFirstTime && !viewModel.isUserDataSaved()) navigation.navigate(MyScreens.EntryScreenFirst.route)
            else navigation.navigate(MyScreens.MainScreen.route) { popUpTo(MyScreens.SplashScreen.route) }
        }, 3500)

    }

}

@Composable
fun MyAnimaShower(name: Int) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(name))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(240.dp)
    )
}
