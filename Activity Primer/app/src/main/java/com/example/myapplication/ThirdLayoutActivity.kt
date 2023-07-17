package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class ThirdLayoutActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ThirdLayoutActivity","Task id is $taskId")
        setContentView(R.layout.third_layout)
        val button3:Button=findViewById(R.id.button3)
        button3.setOnClickListener {
            ActivityCollector.finishAll()
        }
    }
}