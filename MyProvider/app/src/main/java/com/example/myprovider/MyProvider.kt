package com.example.myprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class MyProvider : ContentProvider() {
    private val table1Dir = 0
    private val table1Item = 1
    private val table2Dir = 2
    private val table2Item = 3

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI("com.example.app,provider", "table1", table1Dir)
        uriMatcher.addURI("com.example.app,provider", "table1/#", table1Item)
        uriMatcher.addURI("com.example.app,provider", "table2", table2Dir)
        uriMatcher.addURI("com.example.app,provider", "table/2#", table2Item)
    }

    override fun onCreate(): Boolean {
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArray: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        when(uriMatcher.match(uri)){
            table1Dir -> {}
            table1Item -> {}
            table2Dir -> {}
            table2Item -> {}
        }
        return null
    }

    override fun getType(uri: Uri)=when(uriMatcher.match(uri)){
        table1Dir -> "vnd.android.cursor.dir/vnd.com.example.app,provider.table1"
        table1Item -> "vnd.android.cursor.item/vnd.com.example.app,provider.table1"
        table2Dir -> "vnd.android.cursor.dir/vnd.com.example.app,provider.table2"
        table2Item -> "vnd.android.cursor.item/vnd.com.example.app,provider.table2"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArray: Array<String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArray: Array<String>?
    ): Int {
        return 0
    }

}