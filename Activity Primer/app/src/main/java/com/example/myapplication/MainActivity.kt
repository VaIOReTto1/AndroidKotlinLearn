package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d("MainActivity",this.toString())
        Log.d("MainActivity","Task id is $taskId")
        setContentView(R.layout.first_layout)
        doSomething()
        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            //Toast.makeText(this, "you clicked Button 1", Toast.LENGTH_SHORT).show()

//            //val intent=Intent(this,MainActivity2::class.java)
//            val intent = Intent("com.example.myapplication.ACTION_START")
//            //intent.addCategory("hhh")
//            intent.addCategory("com.example.myapplication.MY_CATEGORY")
//            startActivity(intent)

//            val intent=Intent(Intent.ACTION_VIEW)
//            intent.data= Uri.parse("https://www.baidu.com")
//            startActivity(intent)

//            val intent=Intent(Intent.ACTION_DIAL)
//            intent.data= Uri.parse("tel:10086")
//            startActivity(intent)

//            val intent1=Intent(this,MainActivity2::class.java)
//            val data="Hello MainActivity2"
//            intent1.putExtra("extra_data",data)
//            startActivity(intent1)

//            val intent=Intent(this,MainActivity2::class.java)
//            startActivityForResult(intent,1)

//            val intent=Intent(this,MainActivity2::class.java)
//            startActivity(intent)

            MainActivity2.actionStart(this,"data1","data2")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if(resultCode== RESULT_OK){
                val returnedData=data?.getStringExtra("data_return")
                Log.d("MainActivity","returned data is $returnedData")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "you clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "you clicked Remove", Toast.LENGTH_SHORT)
                .show()
        }
        return true
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity","onRestart")
    }
}