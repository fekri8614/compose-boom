package info.fekri.composeboom.ui.feature.profile

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import info.fekri.composeboom.ui.theme.BackgroundMain
import info.fekri.composeboom.util.ShowAlertWithEditText
import info.fekri.composeboom.util.textIdStyle
import info.fekri.composeboom.util.textLengthStyle

@Composable
fun ProfileScreen() {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(BackgroundMain)
    }

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
                Card(
                    border = BorderStroke(2.dp, Color.White),
                    modifier = Modifier
                        .size(120.dp),
                    shape = RoundedCornerShape(80.dp),
                    elevation = 5.dp,
                ) {
                    AsyncImage(
                        model = viewModel.getProfileImage(),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = textLengthStyle(viewModel.getUserName(), 24),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "@${textIdStyle(viewModel.getUserID())}",
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
        ShowAlertWithEditText(
            title = "Edit your data",
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
                    userNameState.value.toString().isEmpty() ||
                    userNameState.value.toString().isBlank() &&
                    userIdState.value.toString().isEmpty() ||
                    userIdState.value.toString().isBlank()
                ) {
                    Toast.makeText(
                        context.applicationContext,
                        "Please, check your entries out!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.setUserName(userNameState.value)
                    viewModel.setUserID(userIdState.value)
                    viewModel.showChangeDialog.value = false
                }
                clearUserData(viewModel)
            },
            onDismissRequest = {
                if (
                    userIdState.value.toString().isEmpty() &&
                    userNameState.value.toString().isEmpty()
                ) {
                    viewModel.showChangeDialog.value = false
                } else {
                    clearUserData(viewModel)
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

private fun clearUserData(viewModel: ProfileViewModel) {
    viewModel.userId.value = ""
    viewModel.userName.value = ""
}
