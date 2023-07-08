package info.fekri.composeboom.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDrawerItem(var route: String, var icon: ImageVector, var title: String) {
    object Search: NavDrawerItem("searchScreen", Icons.Default.Search, "Let's Search!")
    object More: NavDrawerItem("nav_item_more", Icons.Default.Info, "Wanna find more?")
}
