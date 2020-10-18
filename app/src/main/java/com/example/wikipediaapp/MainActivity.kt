package com.example.wikipediaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import httpUtil.HttpAccessor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import response.ResponseContentJSON

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            var response = sendRequest()
            // 値を取り出す
            println(response.query.pages.values.last().revisions?.last()?.content)
        }

        Thread.sleep(3000)
    }

    private fun sendRequest(): ResponseContentJSON {
        val resposne = HttpAccessor().getRequest()
        return HttpAccessor().getRequest()
    }
}