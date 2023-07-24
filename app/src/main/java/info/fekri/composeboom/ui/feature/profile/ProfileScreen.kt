package info.fekri.composeboom.ui.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.ui.theme.BlueLightBack
import info.fekri.composeboom.util.IconMainApp

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val viewModel = getNavViewModel<ProfileViewModel>()
    val navigation = getNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = BackgroundMain,
        topBar = {
            ProfileTopAppBar {
                navigation.popBackStack()
            }
        }
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 32.dp)
            ) {
                IconMainApp()
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "Mohammad Reza Fekri", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                ))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "@fekri86114", style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ))
            }
            
            Button(onClick = {}, modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 16.dp).align(Alignment.BottomCenter)) {
                Text(text = "Edit", modifier = Modifier.padding(4.dp))
            }
        }
    }

}


// ------------------------------------------------------------------

@Composable
fun ProfileTopAppBar(onBackPressed: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = { Text(text = "Profile") },
        backgroundColor = BackgroundMain,
        elevation = 0.dp
    )
}
