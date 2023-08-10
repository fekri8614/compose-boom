package info.fekri.composeboom.model.data.books


import com.google.gson.annotations.SerializedName

data class ScienceBookResponse(
    @SerializedName("items") val scienceData: List<ScienceBook>
)

data class ScienceBook(
    @SerializedName("accessInfo") val accessInfo: AccessInfo,
    @SerializedName("id") val id: String,
    @SerializedName("selfLink") val selfLink: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo
) {
    data class AccessInfo(
        @SerializedName("accessViewStatus") val accessViewStatus: String,
        @SerializedName("country") val country: String,
        @SerializedName("embeddable") val embeddable: Boolean,
        @SerializedName("epub") val epub: Epub,
        @SerializedName("pdf") val pdf: Pdf,
        @SerializedName("publicDomain") val publicDomain: Boolean,
        @SerializedName("quoteSharingAllowed") val quoteSharingAllowed: Boolean,
        @SerializedName("textToSpeechPermission") val textToSpeechPermission: String,
        @SerializedName("viewability") val viewability: String,
        @SerializedName("webReaderLink") val webReaderLink: String
    ) {
        data class Epub(
            @SerializedName("acsTokenLink") val acsTokenLink: String,
            @SerializedName("downloadLink") val downloadLink: String,
            @SerializedName("isAvailable") val isAvailable: Boolean
        )

        data class Pdf(
            @SerializedName("acsTokenLink") val acsTokenLink: String,
            @SerializedName("downloadLink") val downloadLink: String,
            @SerializedName("isAvailable") val isAvailable: Boolean
        )
    }

    data class VolumeInfo(
        @SerializedName("description") val description: String,
        @SerializedName("imageLinks") val imageLinks: ImageLinks,
        @SerializedName("infoLink") val infoLink: String,
        @SerializedName("language") val language: String,
        @SerializedName("pageCount") val pageCount: Int,
        @SerializedName("previewLink") val previewLink: String,
        @SerializedName("publishedDate") val publishedDate: String,
        @SerializedName("publisher") val publisher: String,
        @SerializedName("ratingsCount") val ratingsCount: Int,
        @SerializedName("subtitle") val subtitle: String,
        @SerializedName("title") val title: String
    ) {
        data class ImageLinks(
            @SerializedName("smallThumbnail") val smallThumbnail: String?,
            @SerializedName("thumbnail") val thumbnail: String?
        )
    }
}