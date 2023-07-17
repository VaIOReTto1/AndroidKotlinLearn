package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity2 : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        val extraData=intent.getStringExtra("extra_data")
        //Log.d("MainActivity2","extra data is $extraData")
        Log.d("MainActivity2","Task id is $taskId")

        val button2:Button=findViewById(R.id.button2)
        button2.setOnClickListener {
//            val intent1=Intent()
//            intent1.putExtra("data_return","hello MainActivity")
//            setResult(RESULT_OK,intent1)
//            finish()

            val intent1=Intent(this,ThirdLayoutActivity::class.java)
            startActivity(intent1)
        }
    }

    //左滑返回
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent2=Intent()
        intent2.putExtra("data_return","hello MainActivity")
        setResult(RESULT_OK,intent2)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity2","onDestory")
    }

    companion object{
        fun actionStart(context: Context,data1:String,data2:String){
//            val intent=Intent(context,MainActivity2::class.java)
//            intent.putExtra("param1",data1)
//            intent.putExtra("param2",data2)

            val intent=Intent(context,MainActivity2::class.java).apply {
                putExtra("param1",data1)
                putExtra("param2",data2)
            }
            context.startActivity(intent)
        }
    }
}