package com.example.wikipediaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import httpUtil.HttpAccessor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import response.ResponseContentJSON

class MainActivity : AppCompatActivity() {

    private var title = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        request_btn.setOnClickListener {
            GlobalScope.launch {
                var response = sendRequest()
                // 値を取り出す
                response.query.pages.values.last().title?.let {
                    title = it
                    print("content: $it")
                }

                print("endend")
            }
        }
        wiki_title.text = "END"
        Thread.sleep(3000)
    }

    private fun sendRequest(): ResponseContentJSON {
        return HttpAccessor().getRequest()
    }

    private fun setTitleTextOnLabel(title: String?) = runBlocking {
        
    }
}