package com.example.webviewtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.active_main.webVie

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        webVie.settings.javaScriptEnabled=true
        webVie.webViewClient= WebViewClient()
        webVie.loadUrl("https://www.baidu.com")
    }
}

