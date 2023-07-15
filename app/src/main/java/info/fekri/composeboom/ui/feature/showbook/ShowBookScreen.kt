package info.fekri.composeboom.ui.feature.showbook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import info.fekri.composeboom.R
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.ui.theme.BlueLightBack
import info.fekri.composeboom.ui.theme.PrimaryDarkColor
import info.fekri.composeboom.ui.theme.YellowBackground

@Composable
fun ShowBookScreen(bookId: String) {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(PrimaryDarkColor)
    }

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        backgroundColor = BackgroundMain,
        topBar = { ShowBookTopBar("book_title") { /*TODO("Handle this")*/ } }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AboutUsBody()
        }
    }

}

@Composable
fun AboutUsBody() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .border(BorderStroke(1.dp, Color.White), shape = RoundedCornerShape(18.dp))
            .background(BlueLightBack, RoundedCornerShape(18.dp)),
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
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "by Writer Name",
                fontSize = 16.sp
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Published on: 2002",
                fontSize = 16.sp
            )
            Text(
                text = "120 pages",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris ...",
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
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
}

@Composable
fun InfoButton(
    txt: String, txtColor: Color = Color.Gray, onButtonClicked: () -> Unit
) {
    Button(onClick = onButtonClicked, modifier = Modifier.clip(RoundedCornerShape(40))) {
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
