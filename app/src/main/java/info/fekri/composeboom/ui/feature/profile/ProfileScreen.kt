package info.fekri.composeboom.ui.feature.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
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
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.util.IconMainApp
import info.fekri.composeboom.util.ShowAlertByEditText

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val viewModel = getNavViewModel<ProfileViewModel>()
    val navigation = getNavController()
    val scaffoldState = rememberScaffoldState()

    val userNameState = viewModel.userName.observeAsState("")
    val userIdState = viewModel.userId.observeAsState(initial = "")

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = BackgroundMain,
        topBar = {
            ProfileTopAppBar {
                navigation.popBackStack()
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 32.dp)
            ) {
                IconMainApp()
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = viewModel.getUserName(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "@${viewModel.getUserID()}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            }

            Button(
                onClick = {
                    viewModel.showChangeDialog.value = true
                }, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(text = "Edit", modifier = Modifier.padding(4.dp))
            }
        }
    }

    if (viewModel.showChangeDialog.value) {
        ShowAlertByEditText(
            title = "Edit your variants",
            btnMsg = "Change",
            edtNameValue = userNameState.value,
            onNameValueChanges = { userName ->
                viewModel.userName.value = userName
            },
            edtIdValue = userIdState.value,
            onIdValueChanges = { userId ->
                viewModel.userId.value = userId
            },
            onConfirmClicked = {
                if (
                    userNameState.value.toString().isNotEmpty() ||
                    userNameState.value.toString().isNotBlank() &&
                    userIdState.value.toString().isEmpty() ||
                    userIdState.value.toString().isBlank()
                ) {
                    Toast.makeText(
                        context.applicationContext,
                        "Please, check your enteries out!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.setUserName(userNameState.value)
                    viewModel.setUserID(userIdState.value)
                    viewModel.showChangeDialog.value = false
                }
            },
            onDismissRequest = {
                if (userIdState.value.toString().isEmpty() && userNameState.value.toString()
                        .isEmpty()
                ) {
                    viewModel.showChangeDialog.value = false
                } else {
                    Toast.makeText(
                        context.applicationContext,
                        "Please, check your entries out!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

}


// ------------------------------------------------------------------

@Composable
fun ProfileTopAppBar(onBackPressed: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = { Text(text = "Profile") },
        backgroundColor = BackgroundMain,
        elevation = 0.dp
    )
}
