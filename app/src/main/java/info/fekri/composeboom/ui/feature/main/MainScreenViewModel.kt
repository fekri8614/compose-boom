package info.fekri.composeboom.ui.feature.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.fekri.composeboom.model.data.books.KidBook
import info.fekri.composeboom.model.data.books.PoemBook
import info.fekri.composeboom.model.data.books.ScienceBook
import info.fekri.composeboom.model.repository.book.BookRepository
import info.fekri.composeboom.model.repository.user.UserRepository
import info.fekri.composeboom.util.coroutineExceptionHandler
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository
) : ViewModel() {
    val dataKids      = mutableStateOf<List<KidBook>>(listOf())
    val dataScience   = mutableStateOf<List<ScienceBook>>(listOf())
    val dataPoems     = mutableStateOf<List<PoemBook>>(listOf())

    val showProgress  = mutableStateOf(false)
    val showNetDialog = mutableStateOf(false)
    val showInfoDialog = mutableStateOf(false)

    val showUiKids    = mutableStateOf(false)
    val showUiScience = mutableStateOf(false)
    val showUiPoems   = mutableStateOf(false)
    val showFromUs    = mutableStateOf(false)

    private fun showKids(): Boolean    = userRepository.getKidsSub()
    private fun showScience(): Boolean = userRepository.getScienceSub()
    private fun showPoems(): Boolean   = userRepository.getPoemsSub()

    init {
        getDataFromNet()
        showFromUs.value = true
    }

    fun getDataFromNet() {
        if (showKids()) {
            showUiKids.value = true
            setupKids()
        }

        if (showScience()) {
            showUiScience.value = true
            setupScience()
        }

        if (showPoems()) {
            showUiPoems.value = true
            setupPoems()
        }
    }

    private fun setupPoems() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showProgress.value = true

            val poemsData   = bookRepository.getPoemBooks()
            dataPoems.value = poemsData

            showProgress.value = false
        }
    }
    private fun setupScience() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showProgress.value = true

            val scienceData   = bookRepository.getScienceBooks()
            dataScience.value = scienceData

            showProgress.value = false
        }
    }
    private fun setupKids() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showProgress.value = true

            val dataKidsFromServer = bookRepository.getKidBooks()
            dataKids.value         = dataKidsFromServer

            showProgress.value = false
        }
    }
}