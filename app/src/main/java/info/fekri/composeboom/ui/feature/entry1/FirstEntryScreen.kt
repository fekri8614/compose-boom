package info.fekri.composeboom.ui.feature.entry1

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.ui.theme.ComposeBoomTheme
import info.fekri.composeboom.util.IconMainApp
import info.fekri.composeboom.util.MyEditText
import info.fekri.composeboom.util.MyScreens
import info.fekri.composeboom.util.NetworkChecker

@Composable
fun FirstEntryScreen(isUserFirstTime: Boolean) {
    val context = LocalContext.current
    val navigation = getNavController()
    val viewModel = getNavViewModel<FirstEntryViewModel>()

    val userName = viewModel.fullName.observeAsState("")
    val userID = viewModel.userID.observeAsState("")
    val userEntry = viewModel.userEntry.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            IconMainApp()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Boom!",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        MyInputs(viewModel)

        Spacer(modifier = Modifier.height(200.dp))

        Button(
            onClick = {
                if (NetworkChecker(context).isInternetConnected) {
                    if (
                        userName.value.isNotEmpty() || userName.value.isNotBlank() ||
                        userID.value.isNotEmpty() || userID.value.isNotBlank() ||
                        userEntry.value.isNotEmpty() || userEntry.value.isNotBlank()
                    ) {
                        navigation.navigate(MyScreens.EntryScreenSecond.route) {
                            popUpTo(MyScreens.EntryScreenFirst.route)
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Please, check your entries!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Please, check your Internet Connection!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Submit!", modifier = Modifier.padding(6.dp))
        }

    }

}

@Composable
fun MyInputs(viewModel: FirstEntryViewModel) {
    val context = LocalContext.current
    val userName = viewModel.fullName.observeAsState("")
    val userID = viewModel.userID.observeAsState("")
    val userEntry = viewModel.userEntry.observeAsState("")

    MyEditText(
        edtValue = userName.value,
        icon = Icons.Default.Person,
        hint = "Your name",
        onValueChanges = { name ->
            viewModel.fullName.value = name
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    MyEditText(
        edtValue = userID.value,
        icon = Icons.Default.Person,
        hint = "Your id",
        onValueChanges = { id ->
            viewModel.userID.value = id
        }
    )

    Spacer(modifier = Modifier.height(30.dp))

    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = "What is 2+2 ?", style = TextStyle(
                color = Color.Gray,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        )
        MyEditText(
            edtValue = userEntry.value,
            icon = Icons.Default.Info,
            hint = "2+2=",
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            onValueChanges = { entry ->
                viewModel.userEntry.value = entry
                if (entry.toInt() != 4) {
                    Toast.makeText(context, "Is 2+2 = $entry?", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Let's continue!", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

