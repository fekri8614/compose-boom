package info.fekri.composeboom.model.repository.book

import info.fekri.composeboom.model.data.SearchedBook
import info.fekri.composeboom.model.data.books.*

interface BookRepository {

    suspend fun getKidBooks(): List<KidBook>
    suspend fun getScienceBooks(): List<ScienceBook>
    suspend fun getPoemBooks(): List<PoemBook>

    suspend fun getSearchedBook(search: String): List<SearchedBook>

}