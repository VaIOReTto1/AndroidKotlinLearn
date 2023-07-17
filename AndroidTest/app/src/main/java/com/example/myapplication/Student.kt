package com.example.myapplication

class Student(sno:String, grade:Int):Person() {
    init {
        println("sno is $sno")
        println("grade is $grade")
    }
}

class Student1(val sno:String,val grade:Int,name:String,age:Int):Person1(name,age) {
    constructor(name: String,age: Int):this("",0,name, age){
        eat()
        println("sno is $sno")
        println("grade is $grade")
    }
    constructor():this("",0){
        eat()
        println("sno is $sno")
        println("grade is $grade")
    }
}

class Student2:Person1{
    constructor(name: String,age: Int):super(name, age){
        eat()
    }
}

class Student3(name:String,age:Int):Person1(name,age),Study{
//    override fun doHomeWork() {
//        println("$name is doing homework.")
//    }

    override fun readBooks() {
        println("$name is reading books.")
    }
}
