package info.fekri.composeboom.ui.feature.entry1

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.fekri.composeboom.model.repository.user.UserRepository

class FirstEntryViewModel(private val userRepository: UserRepository): ViewModel() {
    val fullName = MutableLiveData("")
    val userID = MutableLiveData("")
    val profileImage = mutableStateOf("")
    val showProfImgDialog = mutableStateOf(false)

    fun setupUserData(userName: String, userID: String, profileImg: String) {

        userRepository.saveUserName(userName)
        userRepository.saveUserID(userID)
        userRepository.saveProfileImage(profileImg)

    }

    fun isUserDataSaved(): Boolean {
        return (!userRepository.getUserID().isNullOrEmpty() && !userRepository.getUserName().isNullOrEmpty() && !userRepository.getProfileImage().isNullOrEmpty())
    }

}