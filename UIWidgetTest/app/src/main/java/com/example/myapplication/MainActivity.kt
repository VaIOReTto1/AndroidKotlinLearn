package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.AlertDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> {
//                val editText: EditText = findViewById(R.id.editText)
//                val inputText = editText.text.toString()
//                Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()

//                val imageView:ImageView=findViewById(R.id.imageView)
//                imageView.setImageResource(R.mipmap.vv)

//                val progressBar: ProgressBar = findViewById(R.id.progressBar)
//                if (progressBar.visibility == View.VISIBLE)
//                    progressBar.visibility = View.GONE
//                else
//                    progressBar.visibility = View.VISIBLE

//                val progressBar1: ProgressBar = findViewById(R.id.progressBar1)
//                progressBar1.progress = progressBar1.progress + 10

//                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
//                builder.apply {
//                    setTitle("This is Title")
//                    setMessage("This is Message")
//                    setCancelable(false)//关闭back键关闭对话功能
//                    setPositiveButton("OK") { dialog, which ->
//                    }
//                    setNegativeButton("CANCEL") { dialog, which ->
//                    }
//                    show()
//                }

                val intent=Intent(this,listView::class.java)
                startActivity(intent)
            }
        }
    }
}