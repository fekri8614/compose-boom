package info.fekri.composeboom.ui.feature.pdf

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rizzi.bouquet.HorizontalPDFReader
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.rememberHorizontalPdfReaderState
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.util.PDF_BASE_URL

@Composable
fun ShowPdfScreen(pdfUrl: String) {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(Color.White)
        uiController.setNavigationBarColor(Color.White)
    }
    val viewModel = getNavViewModel<PdfViewModel>()
    viewModel.getPdfUrl(PDF_BASE_URL + pdfUrl)
    val navigation = getNavController()

    val pdfState = rememberHorizontalPdfReaderState(
        resource = ResourceType.Remote(viewModel.pdfUrl.value), isZoomEnable = true
    )
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState, topBar = {
            ShowPdfTopBar {
                viewModel.pdfUrl.value = ""
                navigation.popBackStack()
            }
        }, backgroundColor = Color.White
    ) { padding ->
        HorizontalPDFReader(
            state = pdfState, modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}

@Composable
fun ShowPdfTopBar(onBackPressed: () -> Unit) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = onBackPressed) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    }, title = {}, backgroundColor = Color.White, elevation = 0.dp
    )
}
