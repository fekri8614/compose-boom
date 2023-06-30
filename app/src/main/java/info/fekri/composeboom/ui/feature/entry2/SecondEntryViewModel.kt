package info.fekri.composeboom.ui.feature.entry2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import info.fekri.composeboom.model.repository.user.UserRepository

class SecondEntryViewModel(private val userRepository: UserRepository): ViewModel() {

    val isScienceChecked = mutableStateOf(false)
    val isPoemsChecked = mutableStateOf(false)
    val isHistoryChecked = mutableStateOf(false)
    val isKidsChecked = mutableStateOf(false)

    val showDialog = mutableStateOf(false)

    fun saveScienceSub(science: Boolean) {
        if (science) userRepository.saveScienceSub("Science") else userRepository.saveScienceSub(null)
    }

    fun saveKidsSub(kids: Boolean) {
        if (kids) userRepository.saveKidsSub("Kids") else userRepository.saveKidsSub(null)
    }

    fun savePoems(poems: Boolean) {
        if (poems) userRepository.savePoems("Poems") else userRepository.savePoems(null)
    }

}