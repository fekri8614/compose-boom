package info.fekri.composeboom.ui.feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.burnoo.cokoin.navigation.getNavController

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val navigation = getNavController()

    Column(modifier = modifier.fillMaxSize().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {



    }

}