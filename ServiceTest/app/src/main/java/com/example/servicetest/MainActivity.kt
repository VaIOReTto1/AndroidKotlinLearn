package com.example.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.active_main.bindServiceBtn
import kotlinx.android.synthetic.main.active_main.startIntentServiceBstn
import kotlinx.android.synthetic.main.active_main.startServiceBtn
import kotlinx.android.synthetic.main.active_main.stopServiceBtn
import kotlinx.android.synthetic.main.active_main.unbindServiceBtn

class MainActivity : AppCompatActivity() {
    lateinit var downloadBinder: MyService.DownloadBinder

    private val connection=object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName, service: IBinder?) {
            downloadBinder=service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        startServiceBtn.setOnClickListener {
            val intent=Intent(this,MyService::class.java)
            startService(intent)
        }
        stopServiceBtn.setOnClickListener {
            val intent=Intent(this,MyService::class.java)
            stopService(intent)
        }
        bindServiceBtn.setOnClickListener {
            val intent=Intent(this,MyService::class.java)
            bindService(intent,connection,Context.BIND_AUTO_CREATE) //绑定Service
        }
        unbindServiceBtn.setOnClickListener {
            unbindService(connection) //解绑
        }
        startIntentServiceBstn.setOnClickListener {
            Log.d("MainActivity","Thread id is ${Thread.currentThread().name}")
            val intent=Intent(this,MyIntentService::class.java)
            startService(intent)
        }
    }
}
