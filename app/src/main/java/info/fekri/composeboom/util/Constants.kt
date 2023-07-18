package info.fekri.composeboom.util

import androidx.compose.ui.graphics.Color
import java.io.Serializable

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
val ABOUT_US_ITEMS: List<ThreePair<String, Color, String>> = listOf(
    ThreePair("LinkedIn", Color.Blue, "https://linkedin.com/in/fekri8614"),
    ThreePair("Github", Color.DarkGray, "https://github.com/fekri8614"),
    ThreePair("E-mail", Color.Gray, "mailto:fekri8614@gmail.com")
)
