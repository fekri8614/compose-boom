package info.fekri.composeboom.ui.feature.entry1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstEntryViewModel: ViewModel() {
    val fullName = MutableLiveData("")
    val userID = MutableLiveData("")
    val userEntry = MutableLiveData("")

}