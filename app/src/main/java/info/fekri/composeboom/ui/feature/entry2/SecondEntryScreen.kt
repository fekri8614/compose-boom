package info.fekri.composeboom.ui.feature.entry2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.ui.theme.Shapes
import info.fekri.composeboom.util.IconMainApp

@Composable
fun EntrySecondScreen() {
    val context = LocalContext.current
    val navigation = getNavController()
    val viewModel = getNavViewModel<SecondEntryViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        MainDesign(viewModel)

    }

}

@Composable
fun MainDesign(viewModel: SecondEntryViewModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(top = 16.dp, start = 8.dp)
    ) {
        Text(
            text = "Your favorites:", style = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        )

        SelectableItems(viewModel)
    }
}

@Composable
fun SelectableItems(viewModel: SecondEntryViewModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier.padding(start = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(horizontalArrangement = Arrangement.Center) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.weight(1f)) {
                Text(text = "Science")
                Checkbox(
                    checked = viewModel.isScienceChecked.value,
                    onCheckedChange = { viewModel.isScienceChecked.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colors.primary,
                        uncheckedColor = Color.Gray
                    )
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.weight(1f)) {
                Text(text = "Poems")
                Checkbox(
                    checked = viewModel.isPoemsChecked.value,
                    onCheckedChange = { viewModel.isPoemsChecked.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colors.primary,
                        uncheckedColor = Color.Gray
                    )
                )
            }
        }

        Row(horizontalArrangement = Arrangement.Center) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.weight(1f)) {
                Text(text = "History")
                Checkbox(
                    checked = viewModel.isHistoryChecked.value,
                    onCheckedChange = { viewModel.isHistoryChecked.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colors.primary,
                        uncheckedColor = Color.Gray
                    )
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.weight(1f)) {
                Text(text = "Kids")
                Checkbox(
                    checked = viewModel.isKidsChecked.value,
                    onCheckedChange = { viewModel.isKidsChecked.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colors.primary,
                        uncheckedColor = Color.Gray
                    )
                )
            }
        }

        TextButton(
            onClick = {
                if (viewModel.isKidsChecked.value) {
                    viewModel.showDialog.value = true
                }
            },
            modifier = modifier
                .fillMaxWidth(0.7f)
                .padding(top = 16.dp)
        ) {
            Text(text = "Submit!", style= TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold), modifier = modifier.padding(4.dp))
        }

        if (viewModel.showDialog.value) {
            ShowAnnounceDialog(
                viewModel = viewModel,
                msg = "Kids items is checked off, Kids item is not completed yet."
            )
            viewModel.isKidsChecked.value = false
        }

    }
}

@Composable
fun ShowAnnounceDialog(viewModel: SecondEntryViewModel, msg: String) {
    AlertDialog(
        onDismissRequest = { viewModel.showDialog.value = false },
        title = { Text("Info!") },
        text = { Text(msg) },
        confirmButton = {
            TextButton(onClick = { viewModel.showDialog.value = false }) {
                Text(text = "Close")
            }
        },
        shape = Shapes.medium,
        properties = DialogProperties(dismissOnBackPress = false),
    )
}
