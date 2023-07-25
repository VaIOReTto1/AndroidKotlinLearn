package com.example.playaudiotest

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.active_main.pause
import kotlinx.android.synthetic.main.active_main.play
import kotlinx.android.synthetic.main.active_main.stop

class MainActivity : AppCompatActivity() {
    private val mediaPlayer=MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        initMediaPlayer()
        play.setOnClickListener {
            if (!mediaPlayer.isPlaying)
                mediaPlayer.start()
        }
        pause.setOnClickListener {
            if (mediaPlayer.isPlaying)
                mediaPlayer.pause()
        }
        stop.setOnClickListener {
            if (mediaPlayer.isPlaying)
                mediaPlayer.stop()
        }
    }

    private fun initMediaPlayer(){
        val assetManager=assets
        val fd=assetManager.openFd("music.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}

