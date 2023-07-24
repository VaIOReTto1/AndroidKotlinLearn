package com.example.databasetest

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.active_main.addData
import kotlinx.android.synthetic.main.active_main.createDatabase
import kotlinx.android.synthetic.main.active_main.deleteData
import kotlinx.android.synthetic.main.active_main.queryData
import kotlinx.android.synthetic.main.active_main.replaceData
import kotlinx.android.synthetic.main.active_main.updataData
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        //val dhHelper=MyDatabaseHelper(this,"BookStore.db",1)
        val dhHelper = MyDatabaseHelper(this, "BookStore.db", 3)

        createDatabase.setOnClickListener {
            dhHelper.writableDatabase
        }

        addData.setOnClickListener {
            val db = dhHelper.writableDatabase
            val values1 = ContentValues().apply {
                put("name", "The Da Vinvi Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1)
            val values2 = ContentValues().apply {
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2)
        }

        updataData.setOnClickListener {
            val db = dhHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name=?", arrayOf("The Da Vinvi Code"))
        }

        deleteData.setOnClickListener {
            val db = dhHelper.writableDatabase
            db.delete("Book", "pages>?", arrayOf("500"))
        }

        queryData.setOnClickListener {
            val db = dhHelper.writableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    val name: String = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val price = cursor.getString(cursor.getColumnIndex("price"))
                    val pages = cursor.getString(cursor.getColumnIndex("pages"))
                    Log.d("MainActivity", "Book name is $name")
                    Log.d("MainActivity", "Book author is $author")
                    Log.d("MainActivity", "Book pages is $pages")
                    Log.d("MainActivity", "Book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        replaceData.setOnClickListener {
            val db = dhHelper.writableDatabase
            db.beginTransaction()
            try {
                db.delete("Book", null, null)
//                if(true)
//                    throw NullPointerException()
                val values = contentValuesOf(
                    "name" to "Game of Thrones",
                    "author" to "George Martin",
                    "pages" to 720,
                    "price" to 20.85
                )
                db.insert("Book", null, values)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }

        }
    }
}

