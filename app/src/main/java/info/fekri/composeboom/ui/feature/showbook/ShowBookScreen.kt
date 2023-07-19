package info.fekri.composeboom.ui.feature.showbook

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import info.fekri.composeboom.R
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.ui.theme.BlueLightBack
import info.fekri.composeboom.ui.theme.PrimaryDarkColor
import info.fekri.composeboom.ui.theme.YellowBackground

@Composable
fun ShowBookScreen(/*bookId: String = ""*/) {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(PrimaryDarkColor)
    }

    val context = LocalContext.current
//    val viewModel = getNavViewModel<ShowBookViewModel>()
//    viewModel.getDataBookFromNet(bookId)

    val navigation = getNavController()

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        backgroundColor = BackgroundMain,
        topBar = {
            ShowBookTopBar("book_title") {
                navigation.popBackStack()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            AboutUsBody()
        }
    }

}

@Composable
fun AboutUsBody() {
    var showContent by remember { mutableStateOf(false) }
    val transition = updateTransition(showContent, label = "")

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.8f)
            .border(BorderStroke(1.dp, Color.White), shape = RoundedCornerShape(18.dp))
            .background(BlueLightBack, RoundedCornerShape(18.dp))
            .clickable { showContent = !showContent },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .size(140.dp, 200.dp)
                    .padding(top = 8.dp),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(18.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_imagin1),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Book name",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "by Writer Name",
                fontSize = 14.sp,
                fontWeight = FontWeight.W500
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(1.dp)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = "Published on: 2002",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "120 pages",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showContent) {
            val visibleState = transition.animateDp(
                transitionSpec = {
                    if (false isTransitioningTo true) {
                        tween(durationMillis = 300)
                    } else {
                        spring()
                    }
                }, label = ""
            ) { if (it) 300.dp else 0.dp }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(visibleState.value)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer orem ipsum dolor sit amet, consectetur adipiscing elit. Integer  orem ipsum dolor sit amet, consectetur adipiscing elit. Integer  orem ipsum dolor sit amet, consectetur adipiscing elit. Integer orem ipsum dolor sit amet, consectetur adipiscing elit. Integer orem ipsum dolor sit amet, consectetur adipiscing elit. Integer orem ipsum dolor sit amet, consectetur adipiscing elit. Integer orem ipsum dolor sit amet, consectetur adipiscing elit. Integer orem ipsum dolor sit amet, consectetur adipiscing elit. Integer orem ipsum dolor sit amet, consectetur adipiscing elit. Integer orem ipsum dolor sit amet, consectetur adipiscing elit. Integer orem ipsum dolor sit amet, consectetur adipiscing elit. Integer orem ipsum dolor sit amet, consectetur adipiscing elit. Integer  nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris t amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris ...",
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        InfoButton(txt = "Buy", txtColor = YellowBackground) {
            // TODO("Handle Buy button")
        }
        InfoButton(txt = "Preview") {
            // TODO("Handle Preview button")
        }
        InfoButton(txt = "Read") {
            // TODO("Handle Read button")
        }
    }
}

@Composable
fun InfoButton(
    txt: String, txtColor: Color = Color.White, onButtonClicked: () -> Unit
) {
    Button(
        onClick = onButtonClicked, modifier = Modifier
            .size(120.dp, 60.dp)
            .clip(RoundedCornerShape(40))
    ) {
        Text(
            text = txt,
            color = txtColor,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(8.dp)
        )
    }
}

// --------------------------------------

@Composable
fun ShowBookTopBar(title: String, onBackPressed: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onBackPressed.invoke()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = { Text(text = title) }
    )
}
