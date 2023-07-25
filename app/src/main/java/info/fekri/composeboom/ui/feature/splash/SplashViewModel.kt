package info.fekri.composeboom.ui.feature.splash

import androidx.lifecycle.ViewModel
import info.fekri.composeboom.model.repository.user.UserRepository

class SplashViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun isUserDataSaved(): Boolean = (
            !userRepository.getUserID().isNullOrEmpty() &&
            !userRepository.getUserName().isNullOrEmpty() &&
            userRepository.getScienceSub() &&
            userRepository.getKidsSub() &&
            userRepository.getPoemsSub()
            )

}