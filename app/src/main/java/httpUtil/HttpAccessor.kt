package httpUtil

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import com.fasterxml.jackson.core.JsonEncoding
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONObject
import response.ResponseContentJSON
import url.Path
import java.lang.Exception
import java.net.URLEncoder

class HttpAccessor() {

    private val keyword = "エマ・ワトソン"
    fun getRequest(): ResponseContentJSON {
//        FuelManager.instance.basePath = Path.CONTEXT_PATH_MOCK.value

        Log.d(TAG, "getRequest: start")
        // パラメータ
        val params = listOf(
            "format" to "json",
            "action" to "query",
            "prop" to "revisions",
            "rvprop" to "content",
            "titles" to "エマ・ワトソン"
        )

        try {
            var (request, response, result) = Fuel.get(Path.CONTEXT_PATH_MOCK.value, params).responseJson()
            Log.d(TAG, "request: $request.toString()")
            return when (result) {
                is Result.Failure -> {
                    Log.w(TAG, "request: failed, statusCode: " + response.statusCode)
                    Log.d(TAG, result.getException().toString())
                    Log.d(TAG, "getRequest: end")

                    throw Exception()
                }
                is Result.Success -> {
                    Log.d(TAG, "request: success")
                    // FuelJsonクラスをResponseContentJsonクラスにマッピングする
                    val res = mapJsonObject(result.value.content)
//                    Log.d(TAG, "response: $res")
                    Log.d(TAG, "getRequest: end")
                    res
                }
            }
        } catch (e: Exception) {
            println(e.printStackTrace())
            throw Exception()
        }
    }

    private fun mapJsonObject(data: String): ResponseContentJSON {
        // mapperオブジェクトを作成
        val mapper = ObjectMapper().registerKotlinModule()
        return mapper.readValue(data)
    }
}