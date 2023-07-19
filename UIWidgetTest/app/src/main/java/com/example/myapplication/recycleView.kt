package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.lang.StringBuilder

class recycleView : AppCompatActivity() {
    private val fruitList = ArrayList<Friut1>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycle_view)

        initFruits()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL

        val adapter = FruitAdapter1(fruitList)
        val recyclerView: RecyclerView = findViewById(R.id.recycleVirw)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
//        listView.setOnItemClickListener() { _,_,position,_ ->
//            val friut=fruitList[position]
//            Toast.makeText(this,friut.name,Toast.LENGTH_SHORT).show()
//        }
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Friut1("Apple", R.mipmap.v))
            fruitList.add(Friut1("Apple", R.mipmap.v))
            fruitList.add(Friut1("Banana", R.mipmap.vv))
            fruitList.add(Friut1("Apple", R.mipmap.v))
            fruitList.add(Friut1("Watermelon", R.mipmap.v))
            fruitList.add(Friut1("Apple", R.mipmap.v))
            fruitList.add(Friut1("Apple", R.mipmap.vv))
            fruitList.add(Friut1("Apple", R.mipmap.v))
            fruitList.add(Friut1("Apple", R.mipmap.v))
            fruitList.add(Friut1("Apple", R.mipmap.v))
            fruitList.add(Friut1("Pear", R.mipmap.v))
            fruitList.add(Friut1("Apple", R.mipmap.vv))
        }
    }
}

class Friut1(val name: String, val imageId: Int)

class FruitAdapter1(val fruitList: List<Friut1>) :
    RecyclerView.Adapter<FruitAdapter1.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val friutImage: ImageView = view.findViewById(R.id.imageView)
        val fruitName: TextView = view.findViewById(R.id.testView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.friut_item, parent, false)
        //设置列表间距固定(瀑布流布局和网格布局不需要这个
//        view.layoutParams = RecyclerView.LayoutParams(
//            ViewGroup.LayoutParams.WRAP_CONTENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
        val viewHolder=ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position:Int=viewHolder.bindingAdapterPosition
            val friut=fruitList[position]
            Toast.makeText(parent.context,"You clicked view ${friut.name}",Toast.LENGTH_SHORT).show()
        }
        viewHolder.friutImage.setOnClickListener {
            val position=viewHolder.bindingAdapterPosition
            val friut=fruitList[position]
            Toast.makeText(parent.context,"You clicked view ${friut.name}",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friut = fruitList[position]
        holder.friutImage.setImageResource(friut.imageId)
        holder.fruitName.text = friut.name
    }

    override fun getItemCount(): Int = fruitList.size
}