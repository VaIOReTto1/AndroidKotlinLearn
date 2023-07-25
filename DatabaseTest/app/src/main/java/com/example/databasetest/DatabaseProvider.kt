package com.example.databasetest

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.net.Uri
import android.database.Cursor
import android.os.CancellationSignal
import android.database.sqlite.SQLiteDatabase


class DatabaseProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val categoryDir = 2
    private val categoryItem = 3
    private val authority = "com.example.databasetest.provider"
    private var dbHelper: MyDatabaseHelper? = null

//    private val uriMatcher by lazy {
//        val matcher = UriMatcher(UriMatcher.NO_MATCH)
//        matcher.addURI(authority, "book", bookDir)
//        matcher.addURI(authority, "book/#", bookItem)
//        matcher.addURI(authority, "category", categoryDir)
//        matcher.addURI(authority, "category/#", categoryItem)
//        matcher
//    }

    val uriMatcher by later {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority, "book", bookDir)
        matcher.addURI(authority, "book/#", bookItem)
        matcher.addURI(authority, "category", categoryDir)
        matcher.addURI(authority, "category/#", categoryItem)
        matcher
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) =
        dbHelper?.let {
            val db = it.writableDatabase
            val deleteRows = when (uriMatcher.match(uri)) {
                bookDir -> db.delete("Book", selection, selectionArgs)
                bookItem -> {
                    val bookId = uri.pathSegments[1]
                    db.delete("Book", "id=?", arrayOf(bookId))
                }

                categoryDir -> db.delete("Category", selection, selectionArgs)
                categoryItem -> {
                    val categoryId = uri.pathSegments[1]
                    db.delete("Category", "id=?", arrayOf(categoryId))
                }

                else -> 0
            }
            deleteRows
        } ?: 0

    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        bookDir -> "Vnd.android.cursor.dir/vnd.com.example.databasetest.provider.book"
        bookItem -> "Vnd.android.cursor.item/vnd.com.example.databasetest.provider.book"
        categoryDir -> "Vnd.android.cursor.dir/vnd.com.example.databasetest.provider.category"
        categoryItem -> "Vnd.android.cursor.item/vnd.com.example.databasetest.provider.category"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?) = dbHelper?.let {
        val db = it.writableDatabase
        val uriRetuen = when (uriMatcher.match(uri)) {
            bookDir, bookItem -> {
                val newBookId = db.insert("Book", null, values)
                Uri.parse("content://$authority/book/$newBookId")
            }

            categoryDir, categoryItem -> {
                val newCategoryId = db.insert("Category", null, values)
                Uri.parse("content://$authority/book/$newCategoryId")
            }

            else -> null
        }
        uriRetuen
    }

    override fun onCreate() = context?.let {
        dbHelper = MyDatabaseHelper(it, "BookStore.db", 4)
        true
    } ?: false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = dbHelper?.let {
        val db = it.readableDatabase
        val cursor = when (uriMatcher.match(uri)) {
            bookDir -> db.query("Book", projection, selection, selectionArgs, null, null, sortOrder)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.query("Book", projection, "name=?", arrayOf(bookId), null, null, sortOrder)
            }

            categoryDir -> db.query(
                "Category",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )

            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query(
                    "Category",
                    projection,
                    "name=?",
                    arrayOf(categoryId),
                    null,
                    null,
                    sortOrder
                )
            }

            else -> null
        }
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = dbHelper?.let {
        val db = it.writableDatabase
        val updatedRows = when (uriMatcher.match(uri)) {
            bookDir -> db.update("Book", values, selection, selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.update("Book", values, "name=?", arrayOf(bookId))
            }

            categoryDir -> db.update("Category", values, selection, selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.update("Category", values, "name=?", arrayOf(categoryId))
            }

            else -> 0
        }
        updatedRows
    } ?: 0

    fun <T> later(block:()->T)=Later(block)
}