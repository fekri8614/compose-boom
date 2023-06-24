package info.fekri.composeboom.di

import android.content.Context
import info.fekri.composeboom.model.repository.user.UserRepository
import info.fekri.composeboom.model.repository.user.UserRepositoryImpl
import info.fekri.composeboom.ui.feature.entry1.FirstEntryViewModel
import info.fekri.composeboom.ui.feature.entry2.SecondEntryViewModel
import info.fekri.composeboom.ui.feature.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// define your application modules here
val myModules = module {

    single { androidContext().getSharedPreferences("data_sh_book", Context.MODE_PRIVATE) }

    single<UserRepository> { UserRepositoryImpl(get()) }

    viewModel { SplashViewModel(get()) }
    viewModel { FirstEntryViewModel(get()) }
    viewModel { SecondEntryViewModel() }

}