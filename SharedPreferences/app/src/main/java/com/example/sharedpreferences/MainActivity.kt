package com.example.sharedpreferences

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import kotlinx.android.synthetic.main.active_main.restoreButton
import kotlinx.android.synthetic.main.active_main.saveButon

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)

        saveButon.setOnClickListener {
            val editor = getSharedPreferences("data", MODE_PRIVATE).edit()
//            editor.putString("name", "Tom")
//            editor.putInt("age", 18)
//            editor.putBoolean("married", false)
//            editor.apply()

            getSharedPreferences("data",Context.MODE_PRIVATE).edit{
                putString("name", "Tom")
                putInt("age", 18)
                putBoolean("married", false)
                putString("hhh","hhhhh")
            }
        }

        restoreButton.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d("MainActivity", "name is $name,age is $age,married is $married")
        }
    }
}
