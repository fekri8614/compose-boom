package info.fekri.composeboom.ui.feature.pdf.horizontal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rizzi.bouquet.HorizontalPDFReader
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.rememberHorizontalPdfReaderState
import info.fekri.composeboom.ui.theme.BackgroundMain

@Composable
fun OpenHorizontalPdfScreen(pdfUrl: String) {
    val pdfState = rememberHorizontalPdfReaderState(
        resource = ResourceType.Remote(pdfUrl),
        isZoomEnable = true,
    )

    SelectionContainer(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMain)
    ) {
        HorizontalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundMain)
        )
    }

}