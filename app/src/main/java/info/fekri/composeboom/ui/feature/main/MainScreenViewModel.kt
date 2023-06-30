package info.fekri.composeboom.ui.feature.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import info.fekri.composeboom.model.repository.user.UserRepository

class MainScreenViewModel(private val userRepository: UserRepository) : ViewModel() {
    val dataKids = mutableStateOf<List<String>>(listOf())
    val dataScience = mutableStateOf<List<String>>(listOf())
    val dataPoems = mutableStateOf<List<String>>(listOf()) // will change the list-type.
    // will use Poem/Science/Kid data class(s) instead of String




}