package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.active_main.newsContentFrag
import kotlinx.android.synthetic.main.news_content_frag.contetnLayout
import kotlinx.android.synthetic.main.news_content_frag.newsContent
import kotlinx.android.synthetic.main.news_content_frag.newsTitle
import kotlinx.android.synthetic.main.news_title_frag.newsTitleRecycleView
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.active_main)
    }
}

class News(val title:String,val content:String)

class NewsContentFragment:Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_content_frag,container,false)
    }

    fun refresh(title:String,content:String){
        contetnLayout.visibility=View.VISIBLE
        newsTitle.text=title
        newsContent.text=content
    }
}

class NewsTitleFragment:Fragment(){
    private var isTwoPage=false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_title_frag,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPage=activity?.findViewById<View>(R.id.newsContentLayout)!=null
        val LayoutManager= LinearLayoutManager(activity)
        newsTitleRecycleView.layoutManager=LayoutManager
        val adapter=NewsAdapter(getNews())
        newsTitleRecycleView.adapter=adapter
    }

    private fun getNews():List<News>{
        val newslist=ArrayList<News>()
        for(i in 1..50){
            val news=News("This is news title $i",getRandomLengthString("This is news content $i"))
            newslist.add(news)
        }
        return newslist
    }

    private fun getRandomLengthString(string: String):String{
        val n=(1..20).random()
        val builder=StringBuilder()
        repeat(n){
            builder.append(string)
        }
        return builder.toString()
    }

    inner class NewsAdapter(val newslist:List<News>): RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val newslist:TextView=view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view=LayoutInflater.from(parent.context).inflate(R.layout.news_title_item,parent,false)
            view.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
            val holder=ViewHolder(view)
            holder.itemView.setOnClickListener {
                val news=newslist[holder.adapterPosition]
                if(isTwoPage){
                    val fragment=newsContentFrag as NewsContentFragment
                    fragment.refresh(news.title,news.content)
                }else{
                    Log.d("MainActivity", "${news.title}title")
                    NewsContentActivity.actionStart(parent.context,news.title,news.content)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news=newslist[position]
            holder.newslist.text=news.title
        }

        override fun getItemCount()=newslist.size
    }


}


