package info.fekri.composeboom.ui.feature.profile

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.fekri.composeboom.model.repository.user.UserRepository

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {

    val showChangeDialog = mutableStateOf(false)
    val userName = MutableLiveData("")
    val userId = MutableLiveData("")

    fun setUserName(userName: String) {
        userRepository.saveUserName(userName)
    }
    fun getUserName(): String = userRepository.getUserName() ?: "Add your name"

    fun setUserID(id: String) {
        userRepository.saveUserID(id)
    }
    fun getUserID(): String = userRepository.getUserID() ?: "Add your id"

}