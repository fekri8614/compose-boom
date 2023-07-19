package info.fekri.composeboom.ui.feature.showbook

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.fekri.composeboom.model.repository.book.BookRepository
import info.fekri.composeboom.util.BY_ID_BOOK_DATA_FAKE
import info.fekri.composeboom.util.coroutineExceptionHandler
import kotlinx.coroutines.launch

class ShowBookViewModel(private val bookRepository: BookRepository): ViewModel() {

    val dataShowBook = mutableStateOf(BY_ID_BOOK_DATA_FAKE)
    val showProgress = mutableStateOf(false)
    val downloadPDF = mutableStateOf(false)

    fun getDataBookFromNet(id: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            showProgress.value = true

            val dataBookToSet = bookRepository.getBookInfoById(id)
            dataShowBook.value = dataBookToSet

            showProgress.value = false
        }
    }

}