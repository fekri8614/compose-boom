package info.fekri.composeboom.model.net

import info.fekri.composeboom.model.data.SearchedBookResponse
import info.fekri.composeboom.model.data.books.KidBookResponse
import info.fekri.composeboom.model.data.books.PoemBookResponse
import info.fekri.composeboom.model.data.books.ScienceBookResponse
import info.fekri.composeboom.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/* write your http(s) requests here */
interface ApiService {

    // https://www.googleapis.com/books/v1/volumes?q=YOUR_QUERY

    @GET("/volumes")
    suspend fun getSearchedBook(
        @Query("q") query: String
    ): SearchedBookResponse

    @GET("/volumes")
    suspend fun getScienceBooks(
        @Query("q") query: String = "Science"
    ): ScienceBookResponse

    @GET("/volumes")
    suspend fun getKidBooks(
        @Query("q") query: String = "for kids"
    ): KidBookResponse

    @GET("/volumes")
    suspend fun getPoemBooks(
        @Query("q") query: String = "Poems"
    ): PoemBookResponse

}

fun createApiService(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}