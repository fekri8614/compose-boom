package info.fekri.composeboom.ui.feature.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import info.fekri.composeboom.model.repository.user.UserRepository

class ProfileViewModel(private val userRepository: UserRepository): ViewModel() {

    fun setUserName(userName: String) {
        userRepository.saveUserName(userName)
    }
    fun getUserName(): String = userRepository.getUserName()!!

    fun setUserID(id: String) {
        userRepository.saveUserID(id)
    }
    fun getUserID(): String = userRepository.getUserID()!!
}