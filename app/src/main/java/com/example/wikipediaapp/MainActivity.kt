package com.example.wikipediaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import httpUtil.HttpAccessor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONObject
import response.ResponseContentJSON

class MainActivity : AppCompatActivity() {

    private var title = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        request_btn.setOnClickListener {
            when (val keyword = editSearchKeywordInput.text.toString()) {
                "" -> { return@setOnClickListener }
                else -> {
                    // ボタンの無効化
                    request_btn.isClickable = false
                    // リクエストを送ってviewを更新する
                    runBlocking {
                        setContents(keyword)
                    }
                    request_btn.isClickable = true
                }
            }
        }
    }

    private suspend fun setContents(keyword: String)  {
        // 通信処理
        var response = CoroutineScope(Dispatchers.Default).async { HttpAccessor().getRequest(keyword) }.await()

        // タイトルのセット
        response.query.pages.values.last().title?.let {
            wiki_title.text = it
        }
        // 本文のセット
        response.query.pages.values.last().revisions?.last()?.content?.let {
            contentTextView.text = it
        }
    }
}