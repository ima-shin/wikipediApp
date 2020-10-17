package response

import androidx.annotation.Nullable
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * レスポンスデータクラス
 *
 */
data class ResponseInfoJSON(
    @JsonProperty("batchcomplete") val batchComplete: String,
    @JsonProperty("query") val query: QueryContent
)

data class QueryContent(
    @JsonProperty("pages") @Nullable val pages: Map<String, Contents>
)

data class Contents(
    @JsonProperty("pageid") @Nullable val pageId: Int,
    @JsonProperty("ns") @Nullable val ns: Int,
    @JsonProperty("title") val title: String?,
    @JsonProperty("contentmodel") @Nullable val contentModel: String?,
    @JsonProperty("pagelanguage") @Nullable val pageLanguage: String?,
    @JsonProperty("pagelanguagehtmlcode") @Nullable val pageLanguageHtmlCode: String?,
    @JsonProperty("pagelanguagedir") @Nullable val pageLanguageDir: String?,
    @JsonProperty("touched") @Nullable val touched: String?,
    @JsonProperty("lastrevid") @Nullable val lastRevId: Int,
    @JsonProperty("length") @Nullable val length: Int
)