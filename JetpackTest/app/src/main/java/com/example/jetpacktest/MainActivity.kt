package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(countReserved)
        ).get(MainViewModel::class.java)
        lifecycle.addObserver(MyObserver())

        val plusOneBtn: Button = findViewById(R.id.plusOneBtn)
        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }

        val clearBtn: Button = findViewById(R.id.clearBtn)
        clearBtn.setOnClickListener {
            viewModel.clear()
        }

        val infoText: TextView = findViewById(R.id.infoText)
        viewModel.count.observe(this) { count ->
            infoText.text = count.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.count.value ?: 0)
        }
    }
}