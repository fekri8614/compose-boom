package info.fekri.composeboom.util

sealed class NavDrawerItem(var route: String, var icon: Int, var title: String) {
    object Search: NavDrawerItem("nav_item_search", 0, "Let's Search!")
    object More: NavDrawerItem("nav_item_more", 0, "Wanna find more?")
}
