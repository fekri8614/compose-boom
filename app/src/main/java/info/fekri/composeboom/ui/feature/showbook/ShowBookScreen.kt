package info.fekri.composeboom.ui.feature.showbook

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.model.data.ByIdBook
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.ui.theme.BlueLightBack
import info.fekri.composeboom.ui.theme.PrimaryDarkColor
import info.fekri.composeboom.util.ShowAlertDialog
import info.fekri.composeboom.util.textLengthStyle

@Composable
fun ShowBookScreen(bookId: String) {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(PrimaryDarkColor)
    }

    val context = LocalContext.current
    val viewModel = getNavViewModel<ShowBookViewModel>()
    viewModel.getDataBookFromNet(bookId)

    val navigation = getNavController()

    val stateDataBook = viewModel.dataShowBook

    Scaffold(scaffoldState = rememberScaffoldState(), backgroundColor = BackgroundMain, topBar = {
        ShowBookTopBar(stateDataBook.value.volumeInfo.title) {
            navigation.popBackStack()
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            AboutUsBody(stateDataBook.value) {
                viewModel.downloadPDF.value = true
            }
        }
    }

    if (viewModel.downloadPDF.value) {
        ShowAlertDialog(title = "Are you sure?",
            msg = "The suffix of file is .ascm, which means you need to have Adobe Digital Editions as installed.",
            btnMsg = "I'm Sure",
            onConfirmClicked = {
                viewModel.downloadPDF.value = false
                context.apply {
                    if (stateDataBook.value.accessInfo.pdf.isAvailable) {
                        // download
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(stateDataBook.value.accessInfo.pdf.acsTokenLink)
                            )
                        )
                    } else {
                        Toast.makeText(
                            context, "The PDF file is not available.", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            },
            onDismissRequest = {
                viewModel.downloadPDF.value = false
            })
    }

}

@Composable
fun AboutUsBody(data: ByIdBook, onDownloadPDFClicked: () -> Unit) {
    var showContent by remember { mutableStateOf(false) }
    val transition = updateTransition(showContent, label = "")

    val context = LocalContext.current

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
                AsyncImage(
                    model = data.volumeInfo.imageLinks.thumbnail,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = textLengthStyle(data.volumeInfo.title, 26),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "by ${data.volumeInfo.publisher}",
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
                text = "Published on: ${data.volumeInfo.publishedDate}",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${data.volumeInfo.pageCount} pages",
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
            ) { if (it) 200.dp else 0.dp }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(visibleState.value)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = data.volumeInfo.description,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            InfoButton(txt = "E-book") {
                context.apply {
                    val urlEbook = data.accessInfo.epub
                    if (urlEbook.isAvailable) {
                        onDownloadPDFClicked.invoke()
                    } else {
                        Toast.makeText(context, "The E-book is not available!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
            InfoButton(txt = "Preview") {
                context.apply {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(data.volumeInfo.previewLink)
                        )
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(4.dp))

        Button(
            onClick = {
                // download the pdf
                onDownloadPDFClicked.invoke()
            }, modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text(text = "Download PDF", modifier = Modifier.padding(8.dp))
        }
    }

}

@Composable
fun InfoButton(
    txt: String,
    txtColor: Color = Color.White,
    width: Dp = 120.dp,
    height: Dp = 60.dp,
    onButtonClicked: () -> Unit
) {
    Button(
        onClick = onButtonClicked,
        modifier = Modifier
            .size(width, height)
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
    TopAppBar(navigationIcon = {
        IconButton(onClick = {
            onBackPressed.invoke()
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    }, backgroundColor = BackgroundMain, title = { Text(text = textLengthStyle(title, 20)) })
}
