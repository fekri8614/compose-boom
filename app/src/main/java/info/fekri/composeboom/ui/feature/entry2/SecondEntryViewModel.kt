package info.fekri.composeboom.ui.feature.entry2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import info.fekri.composeboom.model.repository.user.UserRepository

class SecondEntryViewModel(private val userRepository: UserRepository) : ViewModel() {

    val isScienceChecked = mutableStateOf(false)
    val isPoemsChecked = mutableStateOf(false)
    val isHistoryChecked = mutableStateOf(false)
    val isKidsChecked = mutableStateOf(false)

    val showDialog = mutableStateOf(false)

    fun saveScienceSub(science: Boolean) {
        if (science) userRepository.saveScienceSub(true) else userRepository.saveScienceSub(false)
    }

    fun saveKidsSub(kids: Boolean) {
        if (kids) userRepository.saveKidsSub(true) else userRepository.saveKidsSub(false)
    }

    fun savePoems(poems: Boolean) {
        if (poems) userRepository.savePoems(true) else userRepository.savePoems(false)
    }

}