package com.example.androidthreadtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.active_main.changeTextBtn
import kotlinx.android.synthetic.main.active_main.textView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val updateText=1

    val handler=object :Handler(Looper.getMainLooper()){
        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            when(msg.what){
                updateText -> {
                    textView.text = "Nice to meet you"
                }
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        changeTextBtn.setOnClickListener {
            thread {
                val msg=Message()
                msg.what=updateText
                handler.sendMessage(msg)
            }
        }
    }
}
