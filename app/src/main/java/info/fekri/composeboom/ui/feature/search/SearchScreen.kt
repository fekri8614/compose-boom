package info.fekri.composeboom.ui.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.burnoo.cokoin.navigation.getNavController
import info.fekri.composeboom.R
import info.fekri.composeboom.ui.feature.splash.MyAnimaShower
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.ui.theme.Shapes

@Composable
fun SearchScreen() {
    val context = LocalContext.current
    val navigation = getNavController()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundMain),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box {
                IconButton(onClick = { /*TODO("Show dialog about API")*/ }, modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            MyAnimaShower(name = R.raw.search_owl)

            Spacer(modifier = Modifier.height(32.dp))

        }
    }


}


@Composable
fun SearchTextField(
    edtValue: String,
    icon: ImageVector = Icons.Default.Search,
    hint: String,
    onValueChanges: (String) -> Unit
) {
    TextField(
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 16.dp),
        shape = Shapes.medium,
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
    )
}
