package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        val extraData=intent.getStringExtra("extra_data")
        Log.d("MainActivity2","extra data is $extraData")

        val button2:Button=findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent1=Intent()
            intent1.putExtra("data_return","hello MainActivity")
            setResult(RESULT_OK,intent1)
            finish()
        }
    }

    //左滑返回
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent2=Intent()
        intent2.putExtra("data_return","hello MainActivity")
        setResult(RESULT_OK,intent2)
        finish()
    }
}