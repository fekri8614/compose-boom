package info.fekri.composeboom.model.data

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("docs")
    val docs: List<Search>
)

data class Search(
    @SerializedName("already_read_count")
    val alreadyReadCount: Int,
    @SerializedName("author_alternative_name")
    val authorAlternativeName: List<String>,
    @SerializedName("author_facet")
    val authorFacet: List<String>,
    @SerializedName("author_key")
    val authorKey: List<String>,
    @SerializedName("author_name")
    val authorName: List<String>,
    @SerializedName("contributor")
    val contributor: List<String>,
    @SerializedName("cover_edition_key")
    val coverEditionKey: String,
    @SerializedName("cover_i")
    val coverI: Int,
    @SerializedName("currently_reading_count")
    val currentlyReadingCount: Int,
    @SerializedName("ddc")
    val ddc: List<String>,
    @SerializedName("ddc_sort")
    val ddcSort: String,
    @SerializedName("ebook_access")
    val ebookAccess: String,
    @SerializedName("ebook_count_i")
    val ebookCountI: Int,
    @SerializedName("edition_count")
    val editionCount: Int,
    @SerializedName("edition_key")
    val editionKey: List<String>,
    @SerializedName("first_publish_year")
    val firstPublishYear: Int,
    @SerializedName("first_sentence")
    val firstSentence: List<String>,
    @SerializedName("has_fulltext")
    val hasFulltext: Boolean,
    @SerializedName("ia")
    val ia: List<String>,
    @SerializedName("ia_box_id")
    val iaBoxId: List<String>,
    @SerializedName("ia_collection")
    val iaCollection: List<String>,
    @SerializedName("ia_collection_s")
    val iaCollectionS: String,
    @SerializedName("id_amazon")
    val idAmazon: List<String>,
    @SerializedName("id_goodreads")
    val idGoodreads: List<String>,
    @SerializedName("id_librarything")
    val idLibrarything: List<String>,
    @SerializedName("isbn")
    val isbn: List<String>,
    @SerializedName("key")
    val key: String,
    @SerializedName("language")
    val language: List<String>,
    @SerializedName("last_modified_i")
    val lastModifiedI: Int,
    @SerializedName("lcc")
    val lcc: List<String>,
    @SerializedName("lcc_sort")
    val lccSort: String,
    @SerializedName("lccn")
    val lccn: List<String>,
    @SerializedName("lending_edition_s")
    val lendingEditionS: String,
    @SerializedName("lending_identifier_s")
    val lendingIdentifierS: String,
    @SerializedName("number_of_pages_median")
    val numberOfPagesMedian: Int,
    @SerializedName("oclc")
    val oclc: List<String>,
    @SerializedName("person")
    val person: List<String>,
    @SerializedName("person_facet")
    val personFacet: List<String>,
    @SerializedName("person_key")
    val personKey: List<String>,
    @SerializedName("place")
    val place: List<String>,
    @SerializedName("place_facet")
    val placeFacet: List<String>,
    @SerializedName("place_key")
    val placeKey: List<String>,
    @SerializedName("printdisabled_s")
    val printdisabledS: String,
    @SerializedName("public_scan_b")
    val publicScanB: Boolean,
    @SerializedName("publish_date")
    val publishDate: List<String>,
    @SerializedName("publish_place")
    val publishPlace: List<String>,
    @SerializedName("publish_year")
    val publishYear: List<Int>,
    @SerializedName("publisher")
    val publisher: List<String>,
    @SerializedName("publisher_facet")
    val publisherFacet: List<String>,
    @SerializedName("readinglog_count")
    val readinglogCount: Int,
    @SerializedName("seed")
    val seed: List<String>,
    @SerializedName("subject")
    val subject: List<String>,
    @SerializedName("subject_facet")
    val subjectFacet: List<String>,
    @SerializedName("subject_key")
    val subjectKey: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_sort")
    val titleSort: String,
    @SerializedName("title_suggest")
    val titleSuggest: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("_version_")
    val version: Long,
    @SerializedName("want_to_read_count")
    val wantToReadCount: Int
)
