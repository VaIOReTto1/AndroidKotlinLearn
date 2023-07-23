package com.example.broadcastbestpractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.active_login.accountEdit
import kotlinx.android.synthetic.main.active_login.login
import kotlinx.android.synthetic.main.active_login.passwordEdit
import kotlinx.android.synthetic.main.active_login.rememberPass

class LoginActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_login)
        val prefs=getPreferences(Context.MODE_PRIVATE)
        val isRemember=prefs.getBoolean("remember_pass",false)
        if(isRemember){
            val account=prefs.getString("account","")
            val password=prefs.getString("password","")
            Log.d("LoginActivity","account")
            accountEdit.setText(account)
            passwordEdit.setText(password)
            rememberPass.isChecked=true
        }
        login.setOnClickListener {
            val account=accountEdit.text.toString()
            val password=passwordEdit.text.toString()
            if(account=="admin"&&password=="123456"){
                val editor=prefs.edit()
                if (rememberPass.isChecked){
                    editor.putBoolean("remember_pass",true)
                    editor.putString("account",account)
                    editor.putString("password",password)
                }else{
                    editor.clear()
                }
                editor.apply()
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Account or password is invalid",Toast.LENGTH_SHORT).show()
            }
        }
    }
}