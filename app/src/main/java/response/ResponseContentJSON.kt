package response

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * レスポンスモデル
 */
data class ResponseContentJSON (
    @JsonProperty("batchcomplete") val batchComplete: String,
    @JsonProperty("warnings") val warnings: Main,
    @JsonProperty("query") val query: Pages

)

data class Main (
    @JsonProperty("main") val main: Map<String, String>,
    @JsonProperty("revisions") val revisions: Map<String, String>
)

data class Pages (
    @JsonProperty("pages") val pages: Map<String, PageInfo>
)

data class PageInfo (
    @JsonProperty("pageid") val pageId: Int,
    @JsonProperty("ns") val ns: Int,
    @JsonProperty("title") val title: String?,
    @JsonProperty("revisions") val revisions: List<Revisions>?
)

data class Revisions (
    @JsonProperty("contentformat") val contentFormat: String?,
    @JsonProperty("contentmodel") val contentModel: String?,
    @JsonProperty("*") val content: String?
)