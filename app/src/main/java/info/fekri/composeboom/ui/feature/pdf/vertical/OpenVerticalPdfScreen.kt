package info.fekri.composeboom.ui.feature.pdf.vertical

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun OpenVerticalPdfScreen(pdfUrl: String) {
    val context = LocalContext.current
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(pdfUrl),
        isZoomEnable = true
    )
}