package info.fekri.composeboom.ui.feature.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import info.fekri.composeboom.model.repository.user.UserRepository

class MainScreenViewModel(private val userRepository: UserRepository) : ViewModel() {
    val dataKids = mutableStateOf("")
    val dataScience = mutableStateOf("")

    init {
        setupSubData(loadDataFromCache())
    }

    private fun loadDataFromCache(): Pair<String, String> {
        return Pair(userRepository.getScienceSub()!!, userRepository.getKidsSub()!!)
    }

    private fun setupSubData(data: Pair<String, String>) {
        dataScience.value = data.first
        dataKids.value = data.second
    }

}