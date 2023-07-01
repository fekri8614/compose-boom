package info.fekri.composeboom.ui.feature.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.fekri.composeboom.model.data.ISBN
import info.fekri.composeboom.model.data.KidsBook
import info.fekri.composeboom.model.data.PoemBook
import info.fekri.composeboom.model.data.ScienceBook
import info.fekri.composeboom.model.repository.movie.BookRepository
import info.fekri.composeboom.model.repository.user.UserRepository
import info.fekri.composeboom.util.EMPTY_ISBN_DATA
import info.fekri.composeboom.util.coroutineExceptionHandler
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository,
    isNetConnected: Boolean
) : ViewModel() {
    val dataKids = mutableStateOf<List<KidsBook>>(listOf())
    val dataScience = mutableStateOf<List<ScienceBook>>(listOf())
    val dataPoems = mutableStateOf<List<PoemBook>>(listOf())
    val showProgress = mutableStateOf(false)
    val isUserInternetOK = MutableLiveData(true)
    val dataISBN = mutableStateOf<ISBN>(EMPTY_ISBN_DATA)

    private fun showKids(): Boolean = !userRepository.getKidsSub().isNullOrEmpty()
    private fun showScience(): Boolean = !userRepository.getScienceSub().isNullOrEmpty()
    private fun showPoems(): Boolean = !userRepository.getPoemsSub().isNullOrEmpty()

    init {
        checkNetwork(isNetConnected)
        if (showKids()) setupKids()
        if (showPoems()) setupPoems()
        if (showScience()) setupScience()
    }

    private fun checkNetwork(isNetConnected: Boolean) {
        isUserInternetOK.value = isNetConnected
    }

    private fun setupPoems() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showProgress.value = true

            val poemsData = bookRepository.getPoemBooks()
            dataPoems.value = poemsData

            showProgress.value = false
        }
    }
    private fun setupScience() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showProgress.value = true

            val scienceData = bookRepository.getScienceBooks()
            dataScience.value = scienceData

            showProgress.value = false
        }
    }
    private fun setupKids() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showProgress.value = true

            val dataKidsFromServer = bookRepository.getKidBooks()
            dataKids.value = dataKidsFromServer

            showProgress.value = false
        }
    }

    fun getDataISBN(isbn: String) {
        viewModelScope.launch (coroutineExceptionHandler){
            showProgress.value = true

            val dataISBNToSet = bookRepository.getBookByISBN(isbn)
            dataISBN.value = dataISBNToSet.iSBN

            showProgress.value = false
        }
    }

}