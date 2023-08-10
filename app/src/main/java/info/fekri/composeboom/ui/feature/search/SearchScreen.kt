package info.fekri.composeboom.ui.feature.search

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.R
import info.fekri.composeboom.model.data.SearchedBook
import info.fekri.composeboom.ui.feature.splash.MyAnimaShower
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.ui.theme.BlueBackground
import info.fekri.composeboom.ui.theme.Shapes
import info.fekri.composeboom.util.MyScreens
import info.fekri.composeboom.util.NetworkChecker
import info.fekri.composeboom.util.ShowAlertDialog

@Composable
fun SearchScreen() {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(BackgroundMain)
    }

    val context = LocalContext.current
    val navigation = getNavController()
    val viewModel = getNavViewModel<SearchScreenViewModel>()
    val search = viewModel.search.observeAsState(initial = "")
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState, backgroundColor = BackgroundMain, topBar = {
        SearchTopAppBar(onBackPressed = {

            if (viewModel.dataSearch.value.isNotEmpty() && viewModel.search.value.toString()
                    .isNotEmpty() || viewModel.search.value.toString().isNotBlank()
            ) {
                Toast.makeText(
                    context, "Cleared data before going ...", Toast.LENGTH_SHORT
                ).show()

                clearInput(viewModel)

                Toast.makeText(
                    context, "You can press-again to go :-)", Toast.LENGTH_SHORT
                ).show()
            } else {
                navigation.popBackStack()
            }
        }, onInfoClicked = {
            viewModel.showInfoDialog.value = true
        })
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.showProgress.value) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth(1f))
            }
            SearchBody(NetworkChecker(context).isInternetConnected, viewModel) { idBook ->
                navigation.navigate(MyScreens.ShowBookScreen.route + "/" + idBook)
            }
        }
    }

    if (viewModel.showInfoDialog.value) {
        ShowAlertDialog(title = "Information!",
            msg = "The data of books are from Google Libraries. For more detail you can check github page of this project from Profile.",
            btnMsg = "Alright!",
            onConfirmClicked = {
                viewModel.showInfoDialog.value = false
            },
            onDismissRequest = {
                viewModel.showInfoDialog.value = false
            })
    }

    if (viewModel.showNetErrorDialog.value) {
        ShowAlertDialog(title = "Check your Connection!",
            msg = "Please, check your internet connection and try again!",
            btnMsg = "Tray Again",
            onConfirmClicked = {
                viewModel.showNetErrorDialog.value = false
            },
            onDismissRequest = {
                viewModel.showNetErrorDialog.value = false
            })
    }

}

// -------------------------------------------------------------

@Composable
fun SearchBody(
    isNetConnected: Boolean,
    viewModel: SearchScreenViewModel,
    onSearchedBookCLicked: (String) -> Unit
) {
    val search = viewModel.search.observeAsState(initial = "")
    val myAnim = if (isNetConnected) R.raw.search_owl else R.raw.connection_error_owl
    val context = LocalContext.current

    MyAnimaShower(name = myAnim)

    Spacer(modifier = Modifier.height(16.dp))

    SearchContent(
        searchValue = search.value,
        icon = Icons.Default.Search,
        hint = "book name ...",
        onValueChanges = { text ->
            viewModel.search.value = text
        },
        viewModel = viewModel
    )

    if (viewModel.showContent.value) {
        ShowSearchResult(data = viewModel.dataSearch.value, onSearchedBookCLicked = { id ->
            onSearchedBookCLicked.invoke(id)
        })
    }

}

// ----------------------------------------------------------------------

@Composable
fun ShowSearchResult(data: List<SearchedBook>, onSearchedBookCLicked: (String) -> Unit) {
    LazyColumn {
        items(data.size) { index ->
            SearchedBookItem(data[index], onItemClicked = { id ->
                onSearchedBookCLicked.invoke(id)
            })
        }
    }
}

@Composable
fun SearchedBookItem(searchedBook: SearchedBook, onItemClicked: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { onItemClicked.invoke(searchedBook.id) }) {
        AsyncImage(
            model = searchedBook.volumeInfo.imageLinks.thumbnail
                ?: "https://cdn4.vectorstock.com/i/1000x1000/61/88/owl-and-book-vector-26576188.jpg",
            contentDescription = null,
            modifier = Modifier.border(1.dp, color = BlueBackground, shape = RoundedCornerShape(40))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "by ${searchedBook.volumeInfo.publisher}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Published on ${searchedBook.volumeInfo.publishedDate}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${searchedBook.volumeInfo.pageCount} pages",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

// ----------------------------------------------------------------

@Composable
fun SearchContent(
    searchValue: String,
    icon: ImageVector,
    hint: String,
    onValueChanges: (String) -> Unit,
    viewModel: SearchScreenViewModel
) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
    ) {
        SearchItemBody(
            edtValue = searchValue, icon = icon, hint = hint, onValueChanges = onValueChanges
        )

        SearchedBookButton(viewModel) { text ->
            if (text.toString().isEmpty()) {
                Toast.makeText(context, "Please, enter the Book name", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.loadDataSearch(text!!)
            }
        }
    }
}

@Composable
fun SearchedBookButton(viewModel: SearchScreenViewModel, onSearchButtonClicked: (String?) -> Unit) {
    TextButton(
        onClick = {
            onSearchButtonClicked.invoke(viewModel.search.value)
        }, modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(top = 26.dp, start = 4.dp)
    ) {
        Text(text = "Search!", modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun SearchItemBody(
    edtValue: String, icon: ImageVector, hint: String, onValueChanges: (String) -> Unit
) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Done
        ),
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = {
            onValueChanges.invoke(it)
        },
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(top = 16.dp),
        shape = Shapes.medium,
        leadingIcon = { Icon(icon, null) },
    )
}

// ---------------------------------------------------------------------

@Composable
fun SearchTopAppBar(onBackPressed: () -> Unit, onInfoClicked: () -> Unit) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = onBackPressed) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    }, actions = {
        IconButton(onClick = onInfoClicked) {
            Icon(imageVector = Icons.Default.Info, contentDescription = null)
        }
    }, title = {}, backgroundColor = BackgroundMain, elevation = 0.dp
    )
}

private fun clearInput(viewModel: SearchScreenViewModel) {
    viewModel.search.value = ""
    viewModel.showContent.value = false
    viewModel.dataSearch.value = listOf()
}
