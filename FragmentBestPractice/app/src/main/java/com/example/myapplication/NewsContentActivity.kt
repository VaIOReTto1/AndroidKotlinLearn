package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_news_content.newsContentFrag

class NewsContentActivity : AppCompatActivity() {
    companion object{
        fun actionStart(context: Context,title:String,content:String){
            val intent=Intent(context,NewsContentFragment::class.java).apply {
                putExtra("news_tilte",title)
                putExtra("news_content",content)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        val title=intent.getStringExtra("news_title")
        val content=intent.getStringExtra("news_content")
        if(title!=null&&content!=null){
            val fragment=newsContentFrag as NewsContentFragment
            fragment.refresh(title,content)
        }
    }
}