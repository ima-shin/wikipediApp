package httpUtil

import android.content.ContentValues.TAG
import android.util.Log
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

class HttpAccessor() {

    fun getRequest(): ResponseContentJSON {
//        FuelManager.instance.basePath = Path.CONTEXT_PATH_MOCK.value

        Log.d(TAG, "getRequest: start")
        // パラメータ
        val params = listOf(
            "format" to "json",
            "action" to "query",
            "prop" to "revisions",
            "rvprop" to "content",
            "titles" to "%E3%82%A8%E3%83%9E%E3%83%BB%E3%83%AF%E3%83%88%E3%82%BD%E3%83%B3"
        )

        try {
            var (request, _, result) = Fuel.get(Path.CONTEXT_PATH_MOCK.value, params).responseJson()
            println(request)
            return when (result) {
                is Result.Failure -> {
                    Log.w(TAG, "request: failed")
                    Log.d(TAG, result.getException().toString())
                    Log.d(TAG, "getRequest: end")

                    throw Exception()
//                JSONObject(mapOf("message" to result.getException().toString()))
                }
                is Result.Success -> {
                    Log.d(TAG, "request: success")
                    val json = result.value
                    // FuelJsonクラスをJSONオブジェクトに変換してマッピング処理を行う
                    val res = mapJsonObject(result.value.content, result.get().obj())
                    Log.d(TAG, "getRequest: end")

                    res
                }
            }
        } catch (e: Exception) {
            throw Exception()
        }
    }

    private fun mapJsonObject(data: String, json: JSONObject): ResponseContentJSON {
        // mapperオブジェクトを作成
        val mapper = ObjectMapper().registerKotlinModule()
        return mapper.readValue<ResponseContentJSON>(data)
    }
}