package info.fekri.composeboom.ui.feature.entry2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SecondEntryViewModel: ViewModel() {

    val isScienceChecked = mutableStateOf(false)
    val isPoemsChecked = mutableStateOf(false)
    val isHistoryChecked = mutableStateOf(false)
    val isKidsChecked = mutableStateOf(false)

    val showDialog = mutableStateOf(false)



}