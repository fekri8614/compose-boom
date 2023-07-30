package info.fekri.composeboom.ui.feature.pdf.horizontal

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.rememberHorizontalPdfReaderState

@Composable
fun OpenHorizontalPdfScreen(pdfUrl: String) {
    val context = LocalContext.current
    val pdfState = rememberHorizontalPdfReaderState(
        resource = ResourceType.Remote(pdfUrl),
        isZoomEnable = true
    )
}