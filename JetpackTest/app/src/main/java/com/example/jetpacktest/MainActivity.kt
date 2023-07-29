package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(countReserved)
        ).get(MainViewModel::class.java)
        lifecycle.addObserver(MyObserver())

        val plusOneBtn: Button = findViewById(R.id.plusOneBtn)
        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }

        val clearBtn: Button = findViewById(R.id.clearBtn)
        clearBtn.setOnClickListener {
            viewModel.clear()
        }

        val infoText: TextView = findViewById(R.id.infoText)
        viewModel.count.observe(this) { count ->
            infoText.text = count.toString()
        }

        val userDao=AppDatabase.getDatabase(this).userDao()
        val user1=User("Tom","Brady",40)
        val user2=User("Tom","Hanks",63)

        val addDataBtn:Button=findViewById(R.id.addDataBtn)
        addDataBtn.setOnClickListener {
            thread {
                user1.id=userDao.insertUser(user1)
                user2.id=userDao.insertUser(user2)
            }
        }

        val updateBtn:Button=findViewById(R.id.updateBtn)
        updateBtn.setOnClickListener {
            thread {
                user1.age=42
                userDao.updateUser(user1)
            }
        }

        val deleteDataBtn:Button=findViewById(R.id.deleteDatarBtn)
        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        val queryDataBtn:Button=findViewById(R.id.queryDataBtn)
        queryDataBtn.setOnClickListener {
            thread {
                for(user in userDao.loadAllUsers()){
                    Log.d("MainActivity",user.toString())
                }
            }
        }

        val doWorkBtn:Button=findViewById(R.id.doWorkBtn)
        doWorkBtn.setOnClickListener {
            val request=OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            WorkManager.getInstance(this).enqueue(request)
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.count.value ?: 0)
        }
    }
}