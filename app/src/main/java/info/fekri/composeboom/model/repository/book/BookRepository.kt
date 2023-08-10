package info.fekri.composeboom.model.repository.book

import info.fekri.composeboom.model.data.ByIdBook
import info.fekri.composeboom.model.data.SearchedBook
import info.fekri.composeboom.model.data.books.KidBook
import info.fekri.composeboom.model.data.books.PoemBook
import info.fekri.composeboom.model.data.books.ScienceBook

interface BookRepository {

    suspend fun getKidBooks(): List<KidBook>
    suspend fun getScienceBooks(): List<ScienceBook>
    suspend fun getPoemBooks(): List<PoemBook>

    suspend fun getSearchedBook(search: String): List<SearchedBook>
    suspend fun getBookInfoById(id: String): ByIdBook

}