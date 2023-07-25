package com.example.providertest

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.active_main.addData
import kotlinx.android.synthetic.main.active_main.deleteData
import kotlinx.android.synthetic.main.active_main.queryData
import kotlinx.android.synthetic.main.active_main.updateData

class MainActivity : AppCompatActivity() {
    var bookId: String? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        addData.setOnClickListener {
            val uri = Uri.parse("content://com.example.databasetest.provider/book")
            val values = contentValuesOf(
                "name" to "A Clash of Kings",
                "author" to "George Martin",
                "pages" to 1040,
                "price" to 22.85
            )
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
        }

        updateData.setOnClickListener {
            bookId?.let {
                val uri = Uri.parse("content://com.example.databasetest.provider/book/$it")
                val values = contentValuesOf(
                    "name" to "A Storm of Swords",
                    "pages" to 1216,
                    "price" to 24.05
                )
                contentResolver.update(uri, values, null, null)
            }
        }

        deleteData.setOnClickListener {

            bookId?.let {
                val uri = Uri.parse("content://com.example.databasetest.provider/book/$it")
                contentResolver.delete(uri, null, null)
            }
        }

        queryData.setOnClickListener {
            val uri = Uri.parse("content://com.example.databasetest.provider/book")
            contentResolver.query(uri, null, null, null, null)?.apply {
                while (moveToNext()) {
                    val name: String = getString(getColumnIndex("name"))
                    val author = getString(getColumnIndex("author"))
                    val price = getInt(getColumnIndex("price"))
                    val pages = getDouble(getColumnIndex("pages"))
                    Log.d("MainActivity", "Book name is $name")
                    Log.d("MainActivity", "Book author is $author")
                    Log.d("MainActivity", "Book pages is $pages")
                    Log.d("MainActivity", "Book price is $price")
                }
                close()
            }
        }

    }
}

