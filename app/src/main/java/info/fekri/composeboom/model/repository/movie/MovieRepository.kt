package info.fekri.composeboom.model.repository.movie

import info.fekri.composeboom.model.data.*

interface MovieRepository {

    suspend fun getKidBooks(): List<KidsBook>
    suspend fun getScienceBooks(): List<ScienceBook>
    suspend fun getPoemBooks(): List<PoemBook>

    suspend fun getBookByISBN(isbn: String): ISBNInfoBook

}