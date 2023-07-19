package com.example.uibestpractice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException

class MainActivity :  AppCompatActivity() ,View.OnClickListener{
    private val msglist=ArrayList<Msg>()
    private var adapter:MsgAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        initMsg()
        Log.d("MainActivity", msglist[1].context)
        val LayoutManager=LinearLayoutManager(this)
        val recyclerView:RecyclerView=findViewById(R.id.recycleView)
        recyclerView.layoutManager=LayoutManager
        adapter=MsgAdapter(msglist)
        recyclerView.adapter=adapter
        val send:Button=findViewById(R.id.send)
        send.setOnClickListener (this)
    }

    override fun onClick(v:View?){
        val send:Button=findViewById(R.id.send)
        val inputText:TextView=findViewById(R.id.inputText)
        val recyclerView:RecyclerView=findViewById(R.id.recycleView)
        when(v){
            send -> {
                val context=inputText.text.toString()
                if (context.isNotEmpty()){
                    val msg=Msg(context,Msg.TYPE_SEND)
                    msglist.add(msg)
                    adapter?.notifyItemInserted(msglist.size-1)
                    recyclerView.scrollToPosition(msglist.size-1)
                    inputText.setText("")
                }
            }
        }
    }

    private fun initMsg(){
        val msg1=Msg("Hello people",Msg.TYPE_RECEIVE)
        msglist.add(msg1)
        val msg2=Msg("Hello.Who is that",Msg.TYPE_SEND)
        msglist.add(msg2)
        val msg3=Msg("This is Tom.Nice talking to you",Msg.TYPE_RECEIVE)
        msglist.add(msg3)
    }
}

class Msg(val context: String,val type: Int){
    companion object{
        const val TYPE_RECEIVE=0
        const val TYPE_SEND=1
    }
}

class MsgAdapter(val msglist:List<Msg>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    inner class LeftViewHolder(view: View):RecyclerView.ViewHolder(view){
        val leftMsg:TextView=view.findViewById(R.id.leftMsg)
    }
    inner class RightViewHolder(view: View):RecyclerView.ViewHolder(view){
        val rightMsg:TextView=view.findViewById(R.id.rightMsg)
    }

    override fun getItemViewType(position: Int): Int {
        val msg=msglist[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=if (viewType==Msg.TYPE_RECEIVE){
        val view=LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,parent,false)
        view.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        LeftViewHolder(view)
    }else{
        val view=LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,parent,false)
        RightViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg=msglist[position]
        when(holder){
            is LeftViewHolder -> holder.leftMsg.text=msg.context
            is RightViewHolder ->holder.rightMsg.text=msg.context
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount()=msglist.size
}