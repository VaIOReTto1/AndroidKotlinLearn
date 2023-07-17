package com.example.myapplication

open class Person {
    var name=""
    var age=0

    fun eat(){
        println("$name is eating.He is $age years old.")
    }
}

open class Person1(val name:String,val age:Int) {
    fun eat(){
        println("$name is eating.He is $age years old.")
    }
}