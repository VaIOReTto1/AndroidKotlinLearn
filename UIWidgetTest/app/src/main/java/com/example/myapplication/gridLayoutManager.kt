package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.StringBuilder

class gridLayoutManager : AppCompatActivity() {
    private val fruitList = ArrayList<Friut1>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycle_view)

        initFruits()
        val layoutManager = GridLayoutManager(this,3)

        val adapter = FruitAdapter1(fruitList)
        val recyclerView: RecyclerView = findViewById(R.id.recycleVirw)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
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
        val builder= StringBuilder()
        repeat(n){
            builder.append(string)
        }
        return builder.toString()
    }
}