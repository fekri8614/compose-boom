package info.fekri.composeboom.model.data


import com.google.gson.annotations.SerializedName

data class ByIdBook(
    @SerializedName("accessInfo")
    val accessInfo: AccessInfo,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("selfLink")
    val selfLink: String,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo
) {
    data class AccessInfo(
        @SerializedName("country")
        val country: String,
        @SerializedName("epub")
        val epub: Epub,
        @SerializedName("pdf")
        val pdf: Pdf,
        @SerializedName("publicDomain")
        val publicDomain: Boolean,
        @SerializedName("quoteSharingAllowed")
        val quoteSharingAllowed: Boolean,
        @SerializedName("textToSpeechPermission")
        val textToSpeechPermission: String,
        @SerializedName("viewability")
        val viewability: String,
        @SerializedName("webReaderLink")
        val webReaderLink: String
    ) {
        data class Epub(
            @SerializedName("acsTokenLink")
            val acsTokenLink: String,
            @SerializedName("isAvailable")
            val isAvailable: Boolean
        )

        data class Pdf(
            @SerializedName("acsTokenLink")
            val acsTokenLink: String,
            @SerializedName("isAvailable")
            val isAvailable: Boolean
        )
    }

    data class VolumeInfo(
        @SerializedName("description")
        val description: String,
        @SerializedName("imageLinks")
        val imageLinks: ImageLinks,
        @SerializedName("infoLink")
        val infoLink: String,
        @SerializedName("language")
        val language: String,
        @SerializedName("maturityRating")
        val maturityRating: String,
        @SerializedName("pageCount")
        val pageCount: Int,
        @SerializedName("previewLink")
        val previewLink: String,
        @SerializedName("publishedDate")
        val publishedDate: String,
        @SerializedName("publisher")
        val publisher: String,
        @SerializedName("title")
        val title: String
    ) {
        data class ImageLinks(
            @SerializedName("extraLarge")
            val extraLarge: String,
            @SerializedName("large")
            val large: String,
            @SerializedName("medium")
            val medium: String,
            @SerializedName("small")
            val small: String,
            @SerializedName("smallThumbnail")
            val smallThumbnail: String,
            @SerializedName("thumbnail")
            val thumbnail: String
        )
    }
}