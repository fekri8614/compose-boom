package info.fekri.composeboom.ui.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dev.burnoo.cokoin.navigation.getNavController

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val navigation = getNavController()
//    val viewModel = getNavViewModel<MainScreenViewModel>()



}