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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.util.IconMainApp
import info.fekri.composeboom.util.MyEditText
import info.fekri.composeboom.util.MyScreens
import info.fekri.composeboom.util.NetworkChecker

@Composable
fun FirstEntryScreen() {
    val context = LocalContext.current
    val navigation = getNavController()
    val viewModel = getNavViewModel<FirstEntryViewModel>()

    val userName = viewModel.fullName.observeAsState("")
    val userID = viewModel.userID.observeAsState("")

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
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "Let's get to know each other!", style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "I'm Boom, your book owl friend!\nAnd you?", style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(40.dp))

        MyInputs(viewModel)

        Spacer(modifier = Modifier.height(200.dp))

        Button(
            onClick = {
                if (NetworkChecker(context).isInternetConnected) {
                    if (
                        userName.value.isNotEmpty() || userName.value.isNotBlank() ||
                        userID.value.isNotEmpty() || userID.value.isNotBlank()
                    ) {
                        // save user entries
                        viewModel.setupUserData(userName.value, userID.value)

                        if (viewModel.isUserDataSaved()) {
                            navigation.navigate(MyScreens.EntryScreenSecond.route) {
                                popUpTo(MyScreens.SplashScreen.route)
                            }
                        } else {
                            Toast.makeText(context, "Something went Wrong!", Toast.LENGTH_SHORT)
                                .show()
                        }

                    } else {
                        Toast.makeText(
                            context,
                            "Please, check the Inputs!",
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
    val userName = viewModel.fullName.observeAsState("")
    val userID = viewModel.userID.observeAsState("")

    MyEditText(
        edtValue = userName.value,
        icon = Icons.Default.Person,
        hint = "I'm ...",
        onValueChanges = { name ->
            viewModel.fullName.value = name
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    MyEditText(
        edtValue = userID.value,
        icon = Icons.Default.Person,
        hint = "My id-name is ...",
        onValueChanges = { id ->
            viewModel.userID.value = id
        }
    )

}

