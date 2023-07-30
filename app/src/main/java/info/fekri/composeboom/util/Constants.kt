package info.fekri.composeboom.util

import androidx.compose.ui.graphics.Color
import info.fekri.composeboom.model.data.ByIdBook
import java.io.Serializable

const val IS_USER_FIRST_TIME = "IsUsersFirstTime"

const val KEY_USER_NAME = "userName"
const val KEY_USER_ID = "userID"
const val KEY_SHOW_BOOK = "showBookById"
const val KEY_SHOW_PDF_ARG = "showPdfByUrlArgument"

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

// image - pdf url
val FROM_US_DATA : List<Pair<String, String>> = listOf(
    Pair("https://www.istgahekoodak.ir/wp-content/uploads/2019/03/%D9%82%D8%B5%D9%87-%D9%87%D8%A7%DB%8C-%D8%AE%D9%88%D8%A8-3.jpg", "http://dl.istgahekoodak.ir/book/sandbadnameh_istgahekoodak.ir.pdf"),
    Pair("http://www.istgahekoodak.ir/wp-content/uploads/2017/04/%DA%A9%D8%AA%D8%A7%D8%A8-%D8%AF%D9%85-%D9%82%D9%88%D8%B1%D8%A8%D8%A7%D8%BA%D9%87.jpg", "http://dl.istgahekoodak.ir/book/dome_ghoorbaghe%5Bwww.istgahekoodak.ir%5D.pdf"),
    Pair("http://www.istgahekoodak.ir/wp-content/uploads/2017/12/robah-khargoosh02.jpg", "http://istgahekoodak.ir/download2/ebook/Roobah%20Va%20Agha%20Mooshe_istgahekoodak.ir.pdf"),
    Pair("http://www.istgahekoodak.ir/wp-content/uploads/2018/06/little-red-riding-hood1.jpg", "http://dl.istgahekoodak.ir/book/shenel-ghermezi.pdf"),
)

val BY_ID_BOOK_DATA_FAKE = ByIdBook(
    ByIdBook.AccessInfo("", ByIdBook.AccessInfo.Epub("", false), ByIdBook.AccessInfo.Pdf("", false),
        publicDomain = false,
        quoteSharingAllowed = false,
        textToSpeechPermission = "",
        viewability = "",
        webReaderLink = ""
    ),
    "",
    "",
    "",
    ByIdBook.VolumeInfo("", ByIdBook.VolumeInfo.ImageLinks("", "", "", "", "", ""), "", "", "", 0, "", "", "", "")
)
