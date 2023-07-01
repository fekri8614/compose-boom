package info.fekri.composeboom.ui.feature.main

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
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.R
import info.fekri.composeboom.model.data.ISBN
import info.fekri.composeboom.model.data.KidsBook
import info.fekri.composeboom.ui.theme.BlueBackground
import info.fekri.composeboom.ui.theme.GreenBackground
import info.fekri.composeboom.ui.theme.PrimaryDarkColor
import info.fekri.composeboom.ui.theme.Shapes
import info.fekri.composeboom.ui.theme.YellowBackground
import info.fekri.composeboom.util.NetworkChecker
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(PrimaryDarkColor)
    }
    val navigation = getNavController()
    val viewModel =
        getNavViewModel<MainScreenViewModel>(parameters = { parametersOf(NetworkChecker(context).isInternetConnected) })
    val state = rememberCollapsingToolbarScaffoldState()

    val dataKids = viewModel.dataKids.value
    val dataPoems = viewModel.dataPoems.value
    val dataScience = viewModel.dataScience.value
    val dataISBN = viewModel.dataISBN

    CollapsingToolbarScaffold(
        modifier = modifier.fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            val progress = state.toolbarState.progress

            Box {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.img1),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxSize()
                            .pin(),
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
                            fontSize = (20 + (30 - 18) * progress).sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            textAlign = TextAlign.Justify
                        ),
                    )
                    Text(
                        text = "Your book-owl friend!",
                        style = TextStyle(
                            fontSize = (20 + (30 - 18) * progress).sp,
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

            if (viewModel.isUserInternetOK.observeAsState(initial = true).value) {

                if (viewModel.dataKids.value.isNotEmpty()) {
                    KidBookSection(
                        onBookItemClicked = { id ->  },
                        backColor = GreenBackground,
                        data = dataKids,
                        viewModel = viewModel,
                        dataISBN = dataISBN.value
                    )
                }

                if (viewModel.dataPoems.value.isNotEmpty()) {
                    PoemBookSection(
                        onBookItemClicked = {},
                        backColor = BlueBackground,
                        title = "Literature",
                        img = "IMAGE_URL"
                    )
                }

                if (viewModel.dataScience.value.isNotEmpty()) {
                    ScienceBookSection(
                        onBookItemClicked = {},
                        backColor = GreenBackground,
                        title = "Scientific",
                        img = ""
                    )
                }


            } else {
                // show something
            }

        }
    }

}

// --------------------------------------------------------------------

@Composable
fun KidBookSection(
    modifier: Modifier = Modifier,
    onBookItemClicked: (String) -> Unit,
    backColor: Color,
    data: List<KidsBook>,
    viewModel: MainScreenViewModel,
    dataISBN: ISBN
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
                        data = data[it],
                        viewModel = viewModel,
                        dataISBN = dataISBN
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
    data: KidsBook,
    viewModel: MainScreenViewModel,
    dataISBN: ISBN
) {
    viewModel.getDataISBN(data.isbn[0])

    Card(
        modifier = modifier
            .size(width = 160.dp, height = 200.dp)
            .padding(end = 16.dp)
            .clickable {
                onItemClicked.invoke("id") // will pass the real id
            },
        border = BorderStroke(2.dp, YellowBackground),
        elevation = 3.dp
    ) {
        AsyncImage(
            model = dataISBN.bibKey,
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
    title: String,
    img: String
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
                text = title,
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
                items(10) {
                    PoemBookItem(onItemClicked = onBookItemClicked, img = img)
                }
            }
        }
    }
}

@Composable
fun PoemBookItem(modifier: Modifier = Modifier, onItemClicked: (String) -> Unit, img: String) {
    Card(
        modifier = modifier
            .size(width = 160.dp, height = 200.dp)
            .padding(end = 16.dp)
            .clickable {
                onItemClicked.invoke("id") // will pass the real id
            },
        border = BorderStroke(2.dp, YellowBackground),
        elevation = 3.dp
    ) {
        AsyncImage(
            model = img,
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
    title: String,
    img: String
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
                text = title,
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
                items(10) {
                    ScienceBookItem(onItemClicked = onBookItemClicked, img = img)
                }
            }
        }
    }
}

@Composable
fun ScienceBookItem(modifier: Modifier = Modifier, onItemClicked: (String) -> Unit, img: String) {
    Card(
        modifier = modifier
            .size(width = 160.dp, height = 200.dp)
            .padding(end = 16.dp)
            .clickable {
                onItemClicked.invoke("id") // will pass the real id
            },
        border = BorderStroke(2.dp, YellowBackground),
        elevation = 3.dp
    ) {
        AsyncImage(
            model = img,
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
