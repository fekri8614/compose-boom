package info.fekri.composeboom.ui.feature.main

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.developer.kalert.KAlertDialog
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.R
import info.fekri.composeboom.model.data.books.KidBook
import info.fekri.composeboom.model.data.books.PoemBook
import info.fekri.composeboom.model.data.books.ScienceBook
import info.fekri.composeboom.ui.theme.BlueBackground
import info.fekri.composeboom.ui.theme.GreenBackground
import info.fekri.composeboom.ui.theme.PrimaryDarkColor
import info.fekri.composeboom.ui.theme.Shapes
import info.fekri.composeboom.ui.theme.YellowBackground
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Preview
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(PrimaryDarkColor)
    }
    val navigation = getNavController()
    val viewModel = getNavViewModel<MainScreenViewModel>()
    val state = rememberCollapsingToolbarScaffoldState()

    val dataKids = viewModel.dataKids.value
    val dataPoems = viewModel.dataPoems.value
    val dataScience = viewModel.dataScience.value

    CollapsingToolbarScaffold(
        modifier = modifier.fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            val progress = state.toolbarState.progress

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
            Box {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.img1),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxSize()
                            .parallax(ratio = 0.2f),
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "Boom!",
                        style = TextStyle(
                            fontSize = (20 + (20 - 18) * progress).sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            textAlign = TextAlign.Justify
                        ),
                    )
                    Text(
                        text = "Your book-owl friend!",
                        style = TextStyle(
                            fontSize = (20 + (20 - 18) * progress).sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            textAlign = TextAlign.Justify
                        ),
                    )
                }
            }

        },
        toolbarModifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Box {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
                    .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TopCircularIcons()
                if (viewModel.showProgress.value) LinearProgressIndicator(modifier = modifier.fillMaxWidth())

//            if (NetworkChecker(context).isInternetConnected) {

                if (viewModel.showUiKids.value) {
                    KidBookSection(
                        onBookItemClicked = { id -> /*`id` will be used to show more data*/ },
                        backColor = GreenBackground,
                        data = dataKids,
                    )
                }

                if (viewModel.showUiPoems.value) {
                    PoemBookSection(
                        onBookItemClicked = { id -> },
                        backColor = BlueBackground,
                        data = dataPoems
                    )
                }

                if (viewModel.showUiScience.value) {
                    ScienceBookSection(
                        onBookItemClicked = { id -> },
                        backColor = GreenBackground,
                        data = dataScience
                    )
                }

//            } else {
//                MyAnimaShower(name = R.raw.connection_error_owl)
//                viewModel.showDialog.value = true
//            }

            }
            Box(modifier.align(Alignment.BottomEnd).padding(32.dp)) {
                FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = PrimaryDarkColor) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
                }
            }
        }
    }

    if (viewModel.showDialog.value) {
        showMessageDialog(
            context = context,
            title = "Connection!",
            msg = "Please, check if you're connected to Internet!",
            viewModel = viewModel,
            type = KAlertDialog.ERROR_TYPE
        )
    }

}

// --------------------------------------------------------------------

@Composable
fun KidBookSection(
    modifier: Modifier = Modifier,
    onBookItemClicked: (String) -> Unit,
    backColor: Color,
    data: List<KidBook>
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(0.95f)
            .clip(Shapes.large)
            .padding(top = 8.dp),
        color = backColor
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(vertical = 16.dp, horizontal = 8.dp)
        ) {
            Text(
                text = "For Kids",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            LazyRow(
                modifier = modifier.padding(top = 16.dp),
                contentPadding = PaddingValues(start = 8.dp)
            ) {
                items(data.size) {
                    KidBookItem(
                        onItemClicked = onBookItemClicked,
                        data = data[it]
                    )
                }
            }
        }
    }
}

@Composable
fun KidBookItem(
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit,
    data: KidBook
) {
    Card(
        modifier = modifier
            .size(width = 160.dp, height = 200.dp)
            .padding(end = 16.dp)
            .clickable {
                onItemClicked.invoke(data.id)
            },
        border = BorderStroke(2.dp, YellowBackground),
        elevation = 3.dp
    ) {
        val image =
            if (!data.volumeInfo.imageLinks.thumbnail.isNullOrEmpty()) data.volumeInfo.imageLinks.thumbnail
            else "https://cdn4.vectorstock.com/i/1000x1000/61/88/owl-and-book-vector-26576188.jpg"
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

// --------------------------------------------------------------------------------------------

@Composable
fun PoemBookSection(
    modifier: Modifier = Modifier,
    onBookItemClicked: (String) -> Unit,
    backColor: Color,
    data: List<PoemBook>
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(0.95f)
            .clip(Shapes.large)
            .padding(top = 8.dp),
        color = backColor
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(vertical = 16.dp, horizontal = 8.dp)
        ) {
            Text(
                text = "Poems",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            LazyRow(
                modifier = modifier.padding(top = 16.dp),
                contentPadding = PaddingValues(start = 8.dp)
            ) {
                items(data.size) {
                    PoemBookItem(onItemClicked = onBookItemClicked, data = data[it])
                }
            }
        }
    }
}

@Composable
fun PoemBookItem(modifier: Modifier = Modifier, onItemClicked: (String) -> Unit, data: PoemBook) {
    Card(
        modifier = modifier
            .size(width = 160.dp, height = 200.dp)
            .padding(end = 16.dp)
            .clickable {
                onItemClicked.invoke(data.id) // will pass the real id
            },
        border = BorderStroke(2.dp, YellowBackground),
        elevation = 3.dp
    ) {
        val image =
            if (!data.volumeInfo.imageLinks.thumbnail.isNullOrEmpty()) data.volumeInfo.imageLinks.thumbnail
            else "https://cdn4.vectorstock.com/i/1000x1000/61/88/owl-and-book-vector-26576188.jpg"
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

// --------------------------------------------------------------------------------------------

@Composable
fun ScienceBookSection(
    modifier: Modifier = Modifier,
    onBookItemClicked: (String) -> Unit,
    backColor: Color,
    data: List<ScienceBook>
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(0.95f)
            .clip(Shapes.medium)
            .padding(top = 8.dp),
        color = backColor
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(vertical = 16.dp, horizontal = 8.dp)
        ) {
            Text(
                text = "Science",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            LazyRow(
                modifier = modifier.padding(top = 16.dp),
                contentPadding = PaddingValues(start = 8.dp)
            ) {
                items(data.size) {
                    ScienceBookItem(onItemClicked = onBookItemClicked, data = data[it])
                }
            }
        }
    }
}

@Composable
fun ScienceBookItem(
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit,
    data: ScienceBook
) {
    Card(
        modifier = modifier
            .size(width = 160.dp, height = 200.dp)
            .padding(end = 16.dp)
            .clickable {
                onItemClicked.invoke(data.id) // will pass the real id
            },
        border = BorderStroke(2.dp, YellowBackground),
        elevation = 3.dp
    ) {
        val image =
            if (!data.volumeInfo.imageLinks.thumbnail.isNullOrEmpty()) data.volumeInfo.imageLinks.thumbnail
            else "https://cdn4.vectorstock.com/i/1000x1000/61/88/owl-and-book-vector-26576188.jpg"
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}


// -----------------------------------------------------------

@Composable
fun TopCircularIcons() {
    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
        CircularIcon(img = R.drawable.img_all, title = "all") { /*will handle later*/ }
        CircularIcon(img = R.drawable.img_audio_lib, title = "voice lib") { /*will handle later*/ }
        CircularIcon(img = R.drawable.img_watch_list, title = "video lib") { /*will handle later*/ }
        CircularIcon(
            img = R.drawable.img_library_all,
            title = "photo lib"
        ) { /*will handle later*/ }
    }
}

@Composable
fun CircularIcon(
    modifier: Modifier = Modifier,
    img: Int,
    title: String,
    onIconClicked: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .padding(8.dp)
        .clickable { onIconClicked.invoke() }) {
        Card(
            shape = RoundedCornerShape(64.dp),
            elevation = 4.dp,
            border = BorderStroke(1.dp, YellowBackground),
            modifier = modifier.size(70.dp)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        )
    }
}

// -----------------------------------------------------------

fun showMessageDialog(
    context: Context,
    type: Int = KAlertDialog.SUCCESS_TYPE,
    title: String,
    msg: String,
    viewModel: MainScreenViewModel
) {
    val dialog = KAlertDialog(context, type)
    dialog.titleText = title
    dialog.contentText = msg
    dialog.setConfirmClickListener("OK") { viewModel.showDialog.value = false }
    dialog.show()
}
