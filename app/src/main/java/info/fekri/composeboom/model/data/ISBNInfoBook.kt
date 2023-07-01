package info.fekri.composeboom.model.data


import com.google.gson.annotations.SerializedName

data class ISBNInfoBook(
    val iSBN: ISBN
)

data class ISBN(
    @SerializedName("bib_key")
    val bibKey: String,
    @SerializedName("info_url")
    val infoUrl: String,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("preview_url")
    val previewUrl: String
)
