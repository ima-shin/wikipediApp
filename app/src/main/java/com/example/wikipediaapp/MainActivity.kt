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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            var response = sendRequest()
//            var title = response.get
        }

        Thread.sleep(3000)
    }

    private fun sendRequest() {
        val http = HttpAccessor()
        http.getRequest()
    }
}