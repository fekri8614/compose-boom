package info.fekri.composeboom.model.repository.book

import info.fekri.composeboom.model.data.ByIdBook
import info.fekri.composeboom.model.data.SearchedBook
import info.fekri.composeboom.model.data.books.KidBook
import info.fekri.composeboom.model.data.books.PoemBook
import info.fekri.composeboom.model.data.books.ScienceBook
import info.fekri.composeboom.model.net.ApiService

class BookRepositoryImpl(private val apiService: ApiService) : BookRepository {
    override suspend fun getKidBooks(): List<KidBook> {
        val dataKids = apiService.getKidBooks()
        return dataKids.kidsData
    }
    override suspend fun getScienceBooks(): List<ScienceBook> {
        val dataScience = apiService.getScienceBooks()
        return dataScience.scienceData
    }
    override suspend fun getPoemBooks(): List<PoemBook> {
        val dataPoem = apiService.getPoemBooks()
        return dataPoem.poemData
    }

    override suspend fun getSearchedBook(search: String): List<SearchedBook> {
        return apiService.getSearchedBook(search).searchedData
    }

    override suspend fun getBookById(id: String): ByIdBook {
        return apiService.getBookById(id)
    }

}