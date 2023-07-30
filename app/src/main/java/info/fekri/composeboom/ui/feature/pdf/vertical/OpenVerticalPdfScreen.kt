package info.fekri.composeboom.ui.feature.pdf.vertical

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import info.fekri.composeboom.ui.theme.BackgroundMain

@Composable
fun OpenVerticalPdfScreen(pdfUrl: String) {
    val context = LocalContext.current
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(pdfUrl),
        isZoomEnable = true
    )

    SelectionContainer(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMain)
    ) {
        VerticalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundMain)
        )
    }

}