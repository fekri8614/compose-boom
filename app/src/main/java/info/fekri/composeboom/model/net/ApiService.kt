package info.fekri.composeboom.model.net

import info.fekri.composeboom.model.data.ISBNInfoBook
import info.fekri.composeboom.model.data.KidsBookResult
import info.fekri.composeboom.model.data.PoemBookResult
import info.fekri.composeboom.model.data.ScienceBookResult
import info.fekri.composeboom.model.data.Search
import info.fekri.composeboom.model.data.SearchResult
import info.fekri.composeboom.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/* write your http(s) requests here */
interface ApiService {

    @GET("/search.json")
    suspend fun getBookByQuery(
        @Query("q=") query: String,
        @Query("format") format: String = "json"
    ): SearchResult

    @GET("/search.json")
    suspend fun getScienceBooks(
        @Query("q=") query: String = "Science book"
    ): ScienceBookResult

    @GET("/search.json")
    suspend fun getKidBooks(
        @Query("q=") query: String = "Kids"
    ): KidsBookResult

    @GET("/search.json")
    suspend fun getPoemBooks(
        @Query("q=") query: String = "literature"
    ): PoemBookResult

    // http://openlibrary.org/books?bibkeys=ISBN:
    @GET("/books")
    suspend fun getBookByISBN(
        @Query("bibkeys=") bibKey: String
    ): ISBNInfoBook

}

fun createApiService(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}