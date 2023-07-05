package info.fekri.composeboom.ui.feature.entry2

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
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
import info.fekri.composeboom.util.MyScreens

@Composable
fun EntrySecondScreen() {
    val context = LocalContext.current
    val navigation = getNavController()
    val viewModel = getNavViewModel<SecondEntryViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MainDesign(
            viewModel,
            isScienceChecked = { viewModel.saveScienceSub(it) },
            isKidsChecked = { viewModel.saveKidsSub(it) },
            isPoemsChecked = { viewModel.savePoems(it) }
        )

        Button(
            onClick = {
                navigation.navigate(MyScreens.MainScreen.route) {
                    popUpTo(MyScreens.SplashScreen.route)
                }
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text(text = "Submit!", modifier = Modifier.padding(4.dp))
        }

        if (viewModel.isHistoryChecked.value) {
            viewModel.isHistoryChecked.value = false
            Toast.makeText(context, "History item is checked off", Toast.LENGTH_SHORT).show()
        }

    }

}

@Composable
fun MainDesign(
    viewModel: SecondEntryViewModel,
    modifier: Modifier = Modifier,
    isScienceChecked: (Boolean) -> Unit,
    isKidsChecked: (Boolean) -> Unit,
    isPoemsChecked: (Boolean) -> Unit
) {
    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            IconMainApp()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Boom!",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = modifier.height(100.dp))

        SelectableItems(
            viewModel,
            isScienceChecked = isScienceChecked,
            isKidsChecked = isKidsChecked,
            isPoemsChecked = isPoemsChecked
        )

        TextButton(
            onClick = { viewModel.showDialog.value = true },
            modifier = modifier.padding(top = 16.dp)
        ) {
            Text(text = "Couldn't find?", fontSize = 13.sp)
        }

    }
}

@Composable
fun SelectableItems(
    viewModel: SecondEntryViewModel,
    modifier: Modifier = Modifier,
    isScienceChecked: (Boolean) -> Unit,
    isKidsChecked: (Boolean) -> Unit,
    isPoemsChecked: (Boolean) -> Unit
) {

    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Text(
            text = "Your favorites:", style = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        )

        Row(
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier = modifier
                        .width(140.dp)
                        .height(60.dp)
                        .padding(start = 8.dp, top = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(horizontal = 8.dp)
                    ) {
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
                }

                Card(
                    modifier = modifier
                        .width(140.dp)
                        .height(60.dp)
                        .padding(start = 8.dp, top = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(horizontal = 8.dp)
                    ) {
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
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier = modifier
                        .width(140.dp)
                        .height(60.dp)
                        .padding(start = 8.dp, top = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(horizontal = 8.dp)
                    ) {
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
                }

                Card(
                    modifier = modifier
                        .width(140.dp)
                        .height(60.dp)
                        .padding(start = 8.dp, top = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(horizontal = 8.dp)
                    ) {
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
            }

            if (viewModel.showDialog.value) {
                viewModel.isHistoryChecked.value = false
                ShowAnnounceDialog(
                    viewModel = viewModel,
                    msg = "History is checked off, this item is not completed yet"
                )
            }

            if (viewModel.isScienceChecked.value) isScienceChecked(true) else isScienceChecked(false)
            if (viewModel.isKidsChecked.value) isKidsChecked(true) else isKidsChecked(false)
            if (viewModel.isPoemsChecked.value) isPoemsChecked(true) else isPoemsChecked(false)

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
