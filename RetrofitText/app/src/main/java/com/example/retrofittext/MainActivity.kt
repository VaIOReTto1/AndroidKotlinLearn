package com.example.retrofittext

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.active_main.getAppDataBtn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        getAppDataBtn.setOnClickListener {
            val retrofit=Retrofit.Builder()
                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val appService=retrofit.create(AppService::class.java)
            appService.getAppData().enqueue(object :Callback<List<App>>{
                override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                    val list=response.body()
                    Log.d("MainActivity",list.toString())
                    if(list!=null){
                        for (app in list){
                            Log.d("MainActivity", "id is ${app.id}")
                            Log.d("MainActivity", "name is ${app.name}")
                            Log.d("MainActivity", "version is ${app.version}")
                        }
                    }
                }

                override fun onFailure(call: Call<List<App>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}

class App(val name:String,val id:String,val version:String)