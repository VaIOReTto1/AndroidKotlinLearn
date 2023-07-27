package com.example.servicetest

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService:IntentService("MyIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("MyIntentService","Thread id si ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyIntentService","onDestory executed")
    }
}