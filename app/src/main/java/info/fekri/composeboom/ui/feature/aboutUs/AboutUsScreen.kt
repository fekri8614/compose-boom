package info.fekri.composeboom.ui.feature.aboutUs

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import info.fekri.composeboom.R
import info.fekri.composeboom.ui.theme.BlueBackground
import info.fekri.composeboom.ui.theme.PrimaryDarkColor
import info.fekri.composeboom.util.IconMainApp
import info.fekri.composeboom.util.MyScreens
import info.fekri.composeboom.util.NetworkChecker

@Composable
fun AboutUsScreen() {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(PrimaryDarkColor)
    }

    val context = LocalContext.current
    val navigation = getNavController()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AboutTopBar {
            navigation.navigate(MyScreens.MainScreen.route)
        }

        BodyAboutUs()

    }

}

// -----------------------------------------------------

@Composable
fun BodyAboutUs() {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopImagePart()

        SecondInfoContent()
    }
}

@Composable
fun TopImagePart() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(70),
            border = BorderStroke(2.dp, Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_fekri8614),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(140.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Mohammad Reza Fekri",
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                fontSize = 22.sp
            )
        )

        Text(
            text = "(fekri8614)",
            style = TextStyle(
                color = Color.Gray
            )
        )
        
    }

}

@Composable
fun SecondInfoContent() {

}

// -----------------------------------------------

@Composable
fun AboutTopBar(onBackPressed: () -> Unit) {
    val context = LocalContext.current

    TopAppBar(
        elevation = 0.dp,
        backgroundColor = PrimaryDarkColor,
        title = { Text(text = "About Us") },
        navigationIcon = {
            IconButton(onClick = {
                if (NetworkChecker(context).isInternetConnected) onBackPressed.invoke()
                else Toast.makeText(
                    context,
                    "Please, check your Internet Connection!",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}
