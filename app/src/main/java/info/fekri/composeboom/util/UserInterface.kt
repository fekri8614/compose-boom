package info.fekri.composeboom.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import info.fekri.composeboom.R
import info.fekri.composeboom.ui.theme.Shapes

@Preview("IconMainApp")
@Composable
fun IconMainApp() {
    Card(
        border = BorderStroke(2.dp, Color.White),
        modifier = Modifier.size(120.dp),
        shape = RoundedCornerShape(80.dp),
        elevation = 5.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_icon_main_no_back),
            contentDescription = null,
            modifier = Modifier.padding(8.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun MyEditText(
    edtValue: String,
    icon: ImageVector,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onValueChanges: (String) -> Unit
) {
    OutlinedTextField(
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(0.9f),
        shape = Shapes.medium,
        leadingIcon = { Icon(icon, null) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction)
    )
}

@Composable
fun ShowAlertDialog(
    title: String,
    msg: String,
    btnMsg: String,
    onConfirmClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismissRequest,
        title = { Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp) },
        text = { Text(text = msg, fontWeight = FontWeight.Medium, fontSize = 15.sp) },
        confirmButton = {
            TextButton(onClick = onConfirmClicked) {
                Text(text = btnMsg, modifier = Modifier.padding(8.dp), fontSize = 16.sp)
            }
        })
}

@Composable
fun ShowAlertWithEditText(
    title: String,
    btnMsg: String,
    edtNameValue: String,
    onNameValueChanges: (String) -> Unit,
    edtIdValue: String,
    onIdValueChanges: (String) -> Unit,
    onConfirmClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismissRequest,
        title = { Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp) },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Next
                    ),
                    label = { Text("You name ...") },
                    value = edtNameValue,
                    singleLine = true,
                    onValueChange = {
                        onNameValueChanges.invoke(it)
                    },
                    placeholder = { Text("Your name ...") },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp),
                    shape = Shapes.medium,
                    leadingIcon = { Icon(Icons.Default.Person, null) },
                )

                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Done
                    ),
                    label = { Text("You id ...") },
                    value = edtIdValue,
                    singleLine = true,
                    onValueChange = {
                        onIdValueChanges.invoke(it)
                    },
                    placeholder = { Text("Your id ...") },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp),
                    shape = Shapes.medium,
                    leadingIcon = { Icon(Icons.Default.Info, null) },
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmClicked) {
                Text(text = btnMsg, modifier = Modifier.padding(8.dp), fontSize = 16.sp)
            }
        })
}

@Composable
fun ShowProfileDialog(
    title: String,
    imgUrls: List<String>,
    onSelectedImage: (String) -> Unit,
    onConfirmClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismissRequest,
        title = { Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp) },
        text = {
            Column(verticalArrangement = Arrangement.SpaceAround) {
                Text(
                    text = "Choose your favorite:", style = TextStyle(fontSize = 14.sp)
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(imgUrls.size) { index ->
                        ProfileImageItem(imgUrls[index], onSelectedImage = onSelectedImage)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmClicked) {
                Text(text = "Let's Go!", modifier = Modifier.padding(8.dp), fontSize = 16.sp)
            }
        })
}

@Composable
private fun ProfileImageItem(url: String, onSelectedImage: (String) -> Unit) {
    Box(modifier = Modifier
        .size(120.dp)
        .padding(horizontal = 4.dp)
        .clickable { onSelectedImage.invoke(url) }) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
