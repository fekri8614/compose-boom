package info.fekri.composeboom.di

import android.content.Context
import info.fekri.composeboom.model.net.createApiService
import info.fekri.composeboom.model.repository.book.BookRepository
import info.fekri.composeboom.model.repository.book.BookRepositoryImpl
import info.fekri.composeboom.model.repository.user.UserRepository
import info.fekri.composeboom.model.repository.user.UserRepositoryImpl
import info.fekri.composeboom.ui.feature.entry1.FirstEntryViewModel
import info.fekri.composeboom.ui.feature.entry2.SecondEntryViewModel
import info.fekri.composeboom.ui.feature.main.MainScreenViewModel
import info.fekri.composeboom.ui.feature.pdf.OpenPdfViewModel
import info.fekri.composeboom.ui.feature.profile.ProfileViewModel
import info.fekri.composeboom.ui.feature.search.SearchScreenViewModel
import info.fekri.composeboom.ui.feature.showbook.ShowBookViewModel
import info.fekri.composeboom.ui.feature.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// define your application modules here
val myModules = module {

    single { androidContext().getSharedPreferences("data_sh_book", Context.MODE_PRIVATE) }
    single { createApiService() }

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<BookRepository> { BookRepositoryImpl(get()) }

    viewModel { SplashViewModel(get()) }
    viewModel { FirstEntryViewModel(get()) }
    viewModel { SecondEntryViewModel(get()) }
    viewModel { MainScreenViewModel(get(), get()) }
    viewModel { ShowBookViewModel(get()) }
    viewModel { SearchScreenViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { OpenPdfViewModel() }

}