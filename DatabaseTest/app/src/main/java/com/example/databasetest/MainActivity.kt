package com.example.databasetest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.active_main.addData
import kotlinx.android.synthetic.main.active_main.createDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        //val dhHelper=MyDatabaseHelper(this,"BookStore.db",1)
        val dhHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        createDatabase.setOnClickListener {
            dhHelper.writableDatabase
        }
        addData.setOnClickListener {
            val db=dhHelper.writableDatabase
            val values1=ContentValues().apply {
                put("name","The Da Vinvi Code")
                put("author","Dan Brown")
                put("pages",454)
                put("price",16.96)
            }
            db.insert("Book",null,values1)
            Log.d("MainActivity","val1")
            val values2=ContentValues().apply {
                put("name","The Lost Symbol")
                put("author","Dan Brown")
                put("pages",510)
                put("price",19.95)
            }
            db.insert("Book",null,values2)
            Log.d("MainActivity","val2")
        }
    }
}

