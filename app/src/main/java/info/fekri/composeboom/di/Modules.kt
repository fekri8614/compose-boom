package info.fekri.composeboom.di

import info.fekri.composeboom.ui.feature.entry1.FirstEntryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// define your application modules here
val myModules = module {

    viewModel { FirstEntryViewModel() }

}