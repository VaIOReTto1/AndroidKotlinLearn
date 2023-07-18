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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.lang.StringBuilder

class listView : AppCompatActivity() {
    private val fruitList = ArrayList<Friut>()
//    private val data = listOf(
//        "Apple",
//        "Banana",
//        "Orange",
//        "Watermelon",
//        "Pear",
//        "Grape",
//        "Pineapple",
//        "Strawberry",
//        "Cherry",
//        "Mango",
//        "Apple",
//        "Banana",
//        "Orange",
//        "Watermelon",
//        "Pear",
//        "Grape",
//        "Pineapple",
//        "Strawberry",
//        "Cherry",
//        "Mango"
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view)
//        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
//        val listView: ListView = findViewById(R.id.listView)
//        listView.adapter = adapter

        initFruits()
        val adapter = FruitAdapter(this, R.layout.friut_item, fruitList)
        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = adapter
        listView.setOnItemClickListener() { _,_,position,_ ->
            val friut=fruitList[position]
            Toast.makeText(this,friut.name,Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Friut("Apple", R.mipmap.v))
            fruitList.add(Friut("Apple", R.mipmap.v))
            fruitList.add(Friut("Banana", R.mipmap.vv))
            fruitList.add(Friut("Apple", R.mipmap.v))
            fruitList.add(Friut("Watermelon", R.mipmap.v))
            fruitList.add(Friut("Apple", R.mipmap.v))
            fruitList.add(Friut("Apple", R.mipmap.vv))
            fruitList.add(Friut("Apple", R.mipmap.v))
            fruitList.add(Friut("Apple", R.mipmap.v))
            fruitList.add(Friut("Apple", R.mipmap.v))
            fruitList.add(Friut("Pear", R.mipmap.v))
            fruitList.add(Friut("Apple", R.mipmap.vv))
        }
    }
}

class Friut(val name: String, val imageId: Int)

class FruitAdapter(activity: Activity, val resourceInt: Int, data: List<Friut>) :
    ArrayAdapter<Friut>(activity, resourceInt, data) {
    @SuppressLint("ViewHolder")
    inner  class ViewHolder(val friutImage: ImageView ,val fruitName: TextView)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder:ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceInt, parent, false)
            val friutImage: ImageView = view.findViewById(R.id.imageView)
            val fruitName: TextView = view.findViewById(R.id.testView)
            viewHolder=ViewHolder(friutImage,fruitName)
            view.tag=viewHolder
        }
        else {
            view = convertView
            viewHolder=view.tag as ViewHolder
        }

        val friut = getItem(position)
        if (friut != null) {
            viewHolder.friutImage.setImageResource(friut.imageId)
            viewHolder.fruitName.text = friut.name
        }
        return view
    }
}