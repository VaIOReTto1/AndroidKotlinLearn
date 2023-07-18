package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        supportActionBar?.hide()
    }
}

class Titlelayout(context: Context,attributeSet: AttributeSet):LinearLayout(context,attributeSet){
    init {
        LayoutInflater.from(context).inflate(R.layout.title,this)
        val titleBack:Button=findViewById(R.id.titleBack)
        titleBack.setOnClickListener {
            val activity=context as Activity
            activity.finish()
        }
        val titleEdit:Button=findViewById(R.id.titleEdit)
        titleEdit.setOnClickListener {
            Toast.makeText(context,"You clicked Edit button",Toast.LENGTH_SHORT).show()
        }
    }
}
