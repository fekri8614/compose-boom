package info.fekri.composeboom.ui.feature.pdf

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rizzi.bouquet.HorizontalPDFReader
import com.rizzi.bouquet.HorizontalPdfReaderState
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.rememberHorizontalPdfReaderState
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.util.NetworkChecker

@Composable
fun OpenPdfScreen(pdfUrl: String) {
    val viewModel = getNavViewModel<OpenPdfViewModel>()
    viewModel.getPdfUrl(pdfUrl)

    val context = LocalContext.current
    val navigation = getNavController()

    val pdfState = rememberHorizontalPdfReaderState(resource = ResourceType.Remote(viewModel.pdfUrl.value), isZoomEnable = true)
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            OpenPdfTopBar(title = "") {
                if (NetworkChecker(context).isInternetConnected) {
                    navigation.popBackStack()
                    viewModel.pdfUrl.value = ""
                }
            }
        }
    ) { padding ->
        HorizontalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }

}

@Composable
fun OpenPdfTopBar(title: String, onBackPressed: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = { Text(text = title) }, backgroundColor = BackgroundMain,
        elevation = 0.dp
    )
}
