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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.lang.StringBuilder

class staggeredGridLayoutManager : AppCompatActivity() {
    private val fruitList = ArrayList<Friut1>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycle_view)

        initFruits()
        val layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)

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
            fruitList.add(Friut1(getRandomLengthString("Apple"), R.mipmap.v))
            fruitList.add(Friut1(getRandomLengthString("Apple"), R.mipmap.v))
            fruitList.add(Friut1(getRandomLengthString("Pear"), R.mipmap.vv))
            fruitList.add(Friut1(getRandomLengthString("Apple"), R.mipmap.v))
            fruitList.add(Friut1(getRandomLengthString("Apple"), R.mipmap.v))
            fruitList.add(Friut1(getRandomLengthString("Apple"), R.mipmap.v))
            fruitList.add(Friut1(getRandomLengthString("Watermelon"), R.mipmap.v))
            fruitList.add(Friut1(getRandomLengthString("Apple"), R.mipmap.v))
            fruitList.add(Friut1(getRandomLengthString("Apple"), R.mipmap.v))
            fruitList.add(Friut1(getRandomLengthString("Banana"), R.mipmap.v))
            fruitList.add(Friut1(getRandomLengthString("Apple"), R.mipmap.vv))
        }
    }

    private fun getRandomLengthString(string: String):String{
        val  n=(1..20).random()
        val builder=StringBuilder()
        repeat(n){
            builder.append(string)
        }
        return builder.toString()
    }
}