package com.example.broadcastbestpractice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
open class BaseActivity:AppCompatActivity(){
    lateinit var receiver: ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter=IntentFilter()
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE")
        receiver=ForceOfflineReceiver()
        registerReceiver(receiver,intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    inner class ForceOfflineReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context,intent: Intent) {
            Log.d("MainActivity","hh")
            val builder:AlertDialog.Builder= AlertDialog.Builder(context)
            builder.apply {
                setTitle("Warning")
                setMessage("You are forced to be ofline.Please try to login again")
                setCancelable(false)
                setPositiveButton("OK"){_,_->
                    ActivityCollector.allFinish()
                    val i=Intent(context,LoginActivity::class.java)
                    startActivity(i)
                }
                show()
            }
        }
    }
}