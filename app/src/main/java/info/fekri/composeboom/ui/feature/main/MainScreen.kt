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
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.R
import info.fekri.composeboom.model.data.books.KidBook
import info.fekri.composeboom.model.data.books.PoemBook
import info.fekri.composeboom.model.data.books.ScienceBook
import info.fekri.composeboom.ui.feature.splash.MyAnimaShower
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.ui.theme.BlueBackground
import info.fekri.composeboom.ui.theme.GreenBackground
import info.fekri.composeboom.ui.theme.PrimaryDarkColor
import info.fekri.composeboom.ui.theme.Shapes
import info.fekri.composeboom.ui.theme.YellowBackground
import info.fekri.composeboom.util.MyScreens
import info.fekri.composeboom.util.NetworkChecker
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(PrimaryDarkColor)
    }

    val context = LocalContext.current
    val navigation = getNavController()
    val viewModel = getNavViewModel<MainScreenViewModel>()

    val collapsingState = rememberCollapsingToolbarScaffoldState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    val scope = rememberCoroutineScope()

    val dataKids = viewModel.dataKids.value
    val dataPoems = viewModel.dataPoems.value
    val dataScience = viewModel.dataScience.value

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerContent(onItemClicked = { id ->
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                navigation.navigate(id)
            })
        },
        backgroundColor = BackgroundMain,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Open Drawer") },
                icon = { Icon(Icons.Default.Menu, contentDescription = "Menu") },
                onClick = { scope.launch { scaffoldState.drawerState.open() } }
            )
        },
        content = {
            CollapsingToolbarScaffold(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                state = collapsingState,
                scrollStrategy = ScrollStrategy.EnterAlways,
                toolbar = {
                    val progress = collapsingState.toolbarState.progress

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
                                )
                            )
                            Text(
                                text = "Your book-owl friend!",
                                style = TextStyle(
                                    fontSize = (20 + (20 - 18) * progress).sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White,
                                    textAlign = TextAlign.Justify
                                )
                            )
                        }
                    }

                },
                toolbarModifier = modifier.verticalScroll(rememberScrollState())
            ) {
                MyCollapsingBody(
                    modifier,
                    viewModel,
                    context,
                    dataKids,
                    dataPoems,
                    dataScience,
                    onKidItemClicked = { id -> navigation.navigate(id) },
                    onScienceItemClicked = { id -> navigation.navigate(id) },
                    onPoemItemClicked = { id -> navigation.navigate(id) },
                    onAllLibClicked = { /*TODO("Complete onAllLibClicked event")*/ },
                    onVoiceLibClicked = { id -> navigation.navigate(id) },
                    onVideoLibClicked = { id -> navigation.navigate(id) },
                    onPhotoLibClicked = { id -> navigation.navigate(id) },
                )
            }
        }
    )

    if (viewModel.showNetDialog.value) {
        ShowAlertDialog(
            msg = "Please, check your internet connection and try again!",
            btnMsg = "Try Again",
            onConfirmClicked = {
                viewModel.showNetDialog.value = false
                viewModel.getDataFromNet()
                navigation.navigate(MyScreens.MainScreen.route)
            },
            onDismissRequest = {
                viewModel.showNetDialog.value = false
            }
        )
    }

}

// ----------------------------------------------------------

@Composable
fun ShowAlertDialog(
    msg: String,
    btnMsg: String,
    onConfirmClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Information", textAlign = TextAlign.Center) },
        text = { Text(text = msg, textAlign = TextAlign.Justify) },
        confirmButton = {
            Button(onClick = onConfirmClicked) {
                Text(text = btnMsg)
            }
        }
    )
}

// -----------------------------------------------------------

@Composable
fun DrawerContent(onItemClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMain),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Column(
                modifier = Modifier
                    .background(PrimaryDarkColor)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_icon_main_no_back),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        alignment = Alignment.TopStart
                    )
                    Text(
                        text = "Boom!",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(text = "Search and Read book with Boom!", color = Color.White)
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            DrawerItem(
                text = "Search",
                onItemClick = { onItemClicked.invoke(MyScreens.SearchScreen.route) })

            DrawerItem(
                text = "More",
                onItemClick = { onItemClicked.invoke(MyScreens.MoreScreen.route) })
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Developed by ",
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = "fekri8614",
                fontSize = 16.sp,
                color = PrimaryDarkColor,
                fontWeight = FontWeight.Bold
            )
        }


    }
}

@Composable
fun DrawerItem(text: String, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onItemClick() },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

// -----------------------------------------------------------

@Composable
private fun MyCollapsingBody(
    modifier: Modifier,
    viewModel: MainScreenViewModel,
    context: Context,
    dataKids: List<KidBook>,
    dataPoems: List<PoemBook>,
    dataScience: List<ScienceBook>,
    onKidItemClicked: (String) -> Unit,
    onScienceItemClicked: (String) -> Unit,
    onPoemItemClicked: (String) -> Unit,
    onAllLibClicked: () -> Unit,
    onVoiceLibClicked: (String) -> Unit,
    onVideoLibClicked: (String) -> Unit,
    onPhotoLibClicked: (String) -> Unit
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

            TopCircularIcons(
                onAllIconClicked = { onAllLibClicked.invoke() },
                onVoiceLibClicked = { id -> onVoiceLibClicked.invoke(id) },
                onVideoLibClicked = { id -> onVideoLibClicked.invoke(id) },
                onPhotoLibClicked = { id -> onPhotoLibClicked.invoke(id) }
            )
            if (viewModel.showProgress.value) LinearProgressIndicator(modifier = modifier.fillMaxWidth())

            if (NetworkChecker(context).isInternetConnected) {

                if (viewModel.showUiKids.value) {
                    KidBookSection(
                        onBookItemClicked = { id -> onKidItemClicked.invoke(id) },
                        backColor = GreenBackground,
                        data = dataKids,
                    )
                }

                if (viewModel.showUiPoems.value) {
                    PoemBookSection(
                        onBookItemClicked = { id -> onPoemItemClicked.invoke(id) },
                        backColor = BlueBackground,
                        data = dataPoems
                    )
                }

                if (viewModel.showUiScience.value) {
                    ScienceBookSection(
                        onBookItemClicked = { id -> onScienceItemClicked.invoke(id) },
                        backColor = GreenBackground,
                        data = dataScience
                    )
                }

            } else {
                MyAnimaShower(name = R.raw.connection_error_owl)
                viewModel.showNetDialog.value = true
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
fun TopCircularIcons(
    onAllIconClicked: () -> Unit,
    onVoiceLibClicked: (String) -> Unit,
    onVideoLibClicked: (String) -> Unit,
    onPhotoLibClicked: (String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
        CircularIcon(img = R.drawable.img_all, title = "all") { onAllIconClicked.invoke() }
        CircularIcon(
            img = R.drawable.img_audio_lib,
            title = "voice lib"
        ) { onVoiceLibClicked.invoke(MyScreens.VoiceLibScreen.route) }
        CircularIcon(
            img = R.drawable.img_watch_list,
            title = "video lib"
        ) { onVideoLibClicked.invoke(MyScreens.VideoLibScreen.route) }
        CircularIcon(
            img = R.drawable.img_library_all,
            title = "photo lib"
        ) { onPhotoLibClicked.invoke(MyScreens.PhotoLibScreen.route) }
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

// ----------------------------------------------------------------------

@Preview("DrawerPreview")
@Composable
fun DrawerPreview() {
    DrawerContent {

    }
}
