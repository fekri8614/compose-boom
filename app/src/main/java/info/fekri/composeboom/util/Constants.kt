package info.fekri.composeboom.util

import androidx.compose.ui.graphics.Color

const val IS_USER_FIRST_TIME = "IsUsersFirstTime"

const val KEY_USER_NAME = "userName"
const val KEY_USER_ID = "userID"
const val KEY_SHOW_BOOK = "showBookById"

const val KEY_SUB_SCIENCE = "scienceSubjectKey"
const val KEY_SUB_KIDS = "kidsSubjectKey"
const val KEY_SUB_POEMS = "poemsSubjectKey"

// api -->
const val BASE_URL = "https://www.googleapis.com/books/v1/"

// usages -->
val ABOUT_US_ITEMS: List<Pair<String, Color>> = listOf(
    Pair("LinkedIn", Color.Blue),
    Pair("Github", Color.Gray),
    Pair("E-mail", Color.White)
)
