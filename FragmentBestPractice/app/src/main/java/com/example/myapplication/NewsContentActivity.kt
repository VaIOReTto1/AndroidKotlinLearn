package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_news_content.newsContentFrag

class NewsContentActivity : AppCompatActivity() {
    companion object{
        fun actionStart(context: Context,title:String,content:String){
            Log.d("NewsContentActivity", "$title")
            val intent=Intent(context,NewsContentActivity::class.java).apply {
                putExtra("news_title",title)
                putExtra("news_content",content)
            }
            val title=intent.getStringExtra("news_title")
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        Log.d("MainActivity", "jodsj")
        val title=intent.getStringExtra("news_title")
        val content=intent.getStringExtra("news_content")
        Log.d("NewsContentActivity", "$title")
        if(title!=null&&content!=null){
            val fragment=newsContentFrag as NewsContentFragment
            fragment.refresh(title,content)
        }

    }
}