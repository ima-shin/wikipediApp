package httpUtil

import android.os.AsyncTask
import android.os.Parcel
import android.os.Parcelable
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONObject
import kotlin.collections.mapOf

class HttpAccessor() {
//    val ENDPOINT = "http://ja.wikipedia.org/w/api.php&format=json"
    val ENDPOINT = "http://ja.wikipedia.org/w/api.php?format=json&action=query&prop=info&titles=%E3%82%A8%E3%83%9E%E3%83%BB%E3%83%AF%E3%83%88%E3%82%BD%E3%83%B3"

    public fun getRequest() : JSONObject {

        var (request, response, result) = ENDPOINT.httpGet().responseJson()
        println("start to build connection...")

        return when (result) {
            is Result.Failure -> {
                println("failed")
                println(result.getException())
                JSONObject(mapOf("message" to result.getException().toString()))
            }
            is Result.Success -> {
                val json = result.value
                println("json content: " + json.content)
                return result.get().obj()
            }
        }

    }
}