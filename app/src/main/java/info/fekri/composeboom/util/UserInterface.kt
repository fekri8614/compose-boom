package info.fekri.composeboom.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import info.fekri.composeboom.R
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.ui.theme.Shapes
import info.fekri.composeboom.ui.theme.YellowBackground

@Preview
@Composable
fun UserInterfacePreview() {
    IconMainApp()
}

@Composable
fun IconMainApp() {
    Card(
        border = BorderStroke(2.dp, YellowBackground),
        backgroundColor = BackgroundMain,
        modifier = Modifier.size(height = 100.dp, width = 140.dp),
        shape = RoundedCornerShape(80.dp),
        elevation = 4.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_icon),
            contentDescription = null,
            modifier = Modifier.padding(8.dp),
            contentScale = ContentScale.Fit
        )
    }
}



