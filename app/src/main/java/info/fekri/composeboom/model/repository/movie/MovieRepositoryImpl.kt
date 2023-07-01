package info.fekri.composeboom.model.repository.movie

import info.fekri.composeboom.model.data.*
import info.fekri.composeboom.model.net.ApiService

class MovieRepositoryImpl(private val apiService: ApiService) : MovieRepository {

    override suspend fun getKidBooks(): List<KidsBook> {
        val kidsFromService = apiService.getKidBooks()
        return kidsFromService.docs
    }

    override suspend fun getScienceBooks(): List<ScienceBook> {
        val scienceFromServer = apiService.getScienceBooks()
        return scienceFromServer.docs
    }

    override suspend fun getPoemBooks(): List<PoemBook> {
        val poemFromServer = apiService.getPoemBooks()
        return poemFromServer.docs
    }

    override suspend fun getBookByISBN(isbn: String): ISBNInfoBook =
        apiService.getBookByISBN("ISBN:$isbn")

}