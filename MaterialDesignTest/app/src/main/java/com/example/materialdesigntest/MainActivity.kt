package com.example.materialdesigntest

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import java.time.Duration
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)

        val drawerLayout: androidx.drawerlayout.widget.DrawerLayout =
            findViewById(R.id.drawerLayout)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.mipmap.ic_menu)
        }

        val navigator: NavigationView = findViewById(R.id.navView)
        navigator.setCheckedItem(R.id.navCall)
        navigator.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            view.showSnackbar("Data deleted","Undo"){
                "FAB clicked".showToase(this)
            }
        }

        initFruits()
        val layoutManager=GridLayoutManager(this,2)
        val adapter=FruitAdapter(this,fruitList)
        val recyclerView=findViewById<RecyclerView>(R.id.recycleView)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=adapter

        val swipeRefresh=findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefresh.setColorSchemeResources(com.google.android.material.R.color.primary_material_dark)
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    val fruits = mutableListOf(
        Fruit("Apple", R.mipmap.apple),
        Fruit("Banana", R.mipmap.banana),
        Fruit("Orange", R.mipmap.orange),
        Fruit("Watermelon", R.mipmap.watermelon),
        Fruit("Pear", R.mipmap.pear),
        Fruit("Grape", R.mipmap.grape),
        Fruit("Pineapple", R.mipmap.pineapple),
        Fruit("Strawberry", R.mipmap.strawberry),
        Fruit("Cherry", R.mipmap.cherry),
        Fruit("Mango", R.mipmap.mango)
    )

    val fruitList=ArrayList<Fruit>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show()
            android.R.id.home -> {
                val drawerLayout: androidx.drawerlayout.widget.DrawerLayout =
                    findViewById(R.id.drawerLayout)
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }

    private fun initFruits(){
        fruitList.clear()
        repeat(50){
            val index=(0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshFruits(adapter: FruitAdapter){
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                val swipeRefresh:SwipeRefreshLayout=findViewById(R.id.swipeRefresh)
                swipeRefresh.isRefreshing=false
            }
        }
    }

    private fun String.showToase(context:Context,duration: Int=Toast.LENGTH_SHORT){
        Toast.makeText(context,this,duration).show()
    }

    private fun Int.showToase(context:Context,duration: Int=Toast.LENGTH_SHORT){
        Toast.makeText(context,this,duration).show()
    }

    private fun View.showSnackbar(text:String,actionText:String?=null,duration: Int=Snackbar.LENGTH_SHORT,block:(()->Unit)?=null){
        val snackbar=Snackbar.make(this,text,duration)
        if (actionText!=null&&block!=null){
            snackbar.setAction(actionText){
                block()
            }
        }
        snackbar.show()
    }

    private fun View.showSnackbar(text:Int,actionText:String?=null,duration: Int=Snackbar.LENGTH_SHORT,block:(()->Unit)?=null){
        val snackbar=Snackbar.make(this,text,duration)
        if (actionText!=null&&block!=null){
            snackbar.setAction(actionText){
                block()
            }
        }
        snackbar.show()
    }
}

class Fruit(val name: String, val imageId: Int)