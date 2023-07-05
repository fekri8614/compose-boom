package info.fekri.composeboom.util

import info.fekri.composeboom.model.data.ISBN
import info.fekri.composeboom.model.data.ISBNInfoBook

const val IS_USER_FIRST_TIME = "IsUsersFirstTime"

const val KEY_USER_NAME = "userName"
const val KEY_USER_ID = "userID"

const val KEY_SUB_SCIENCE = "scienceSubjectKey"
const val KEY_SUB_KIDS = "kidsSubjectKey"
const val KEY_SUB_POEMS = "poemsSubjectKey"

// api -->
const val BASE_URL = "https://www.googleapis.com/books/v1"

val EMPTY_ISBN_DATA = ISBN("", "", "", "")
