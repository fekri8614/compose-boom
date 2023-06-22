package info.fekri.composeboom.ui.feature.entry1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.fekri.composeboom.model.repository.user.UserRepository

class FirstEntryViewModel(private val userRepository: UserRepository): ViewModel() {
    val fullName = MutableLiveData("")
    val userID = MutableLiveData("")

    fun setupUserData(userName: String, userID: String) {

        userRepository.saveUserName(userName)
        userRepository.saveUserID(userID)

    }

    fun isUserDataSaved(): Boolean {
        return (!userRepository.getUserID().isNullOrEmpty() && !userRepository.getUserName().isNullOrEmpty())
    }

}