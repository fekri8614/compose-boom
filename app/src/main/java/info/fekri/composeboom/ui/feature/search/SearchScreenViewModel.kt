package info.fekri.composeboom.ui.feature.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.fekri.composeboom.model.data.SearchedBook
import info.fekri.composeboom.model.repository.book.BookRepository
import info.fekri.composeboom.util.coroutineExceptionHandler
import kotlinx.coroutines.launch

class SearchScreenViewModel(private val bookRepository: BookRepository): ViewModel() {
    val search = MutableLiveData("")
    val dataSearch = mutableStateOf<List<SearchedBook>>(listOf())
    val showProgress = mutableStateOf(false)
    val showContent = mutableStateOf(false)

    fun loadDataSearch(search: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            showProgress.value = true

            val dataSearchToSet = bookRepository.getSearchedBook(search)
            dataSearch.value = dataSearchToSet

            showContent.value = true
            showProgress.value = false
        }
    }

}