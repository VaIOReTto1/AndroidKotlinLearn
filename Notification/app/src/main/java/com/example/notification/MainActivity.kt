package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.active_main.sendNotice

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            val channel2 =
                NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel2)
//            manager.cancel(1)
        }
        sendNotice.setOnClickListener {
            intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            val notification = NotificationCompat.Builder(this, "important")
                .setContentTitle("This is content title")
                .setStyle(
                    NotificationCompat.BigTextStyle().bigText(
                        "Learn how to build notification,send and sync data, and use" +
                                "void actions .Get the official Android IDE and developer tools to build apps for Android."
                    )
                )
                .setSmallIcon(R.drawable.v)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.vv))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.v))
                )
                .build()
            manager.notify(1, notification)
        }
    }
}
