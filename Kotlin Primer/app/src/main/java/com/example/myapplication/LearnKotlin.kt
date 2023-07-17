package com.example.myapplication

import java.util.Locale
import kotlin.math.max

fun Max(a: Int, b: Int) = max(a, b)

fun Max1(a: Int, b: Int) = if (a > b) a else b

fun test1() {
    val a = 10
    val b = 20
    val value = Max1(a, b)
    println(value)
}

fun getSrore(name: String): Int = when (name) {
    "Tom" -> 87
    "Jim" -> 77
    else -> 0
}

fun test2() {
    val name = "Tom"
    val store: Int = getSrore(name)
    val store1: Int = getSrore("hh")
    println("$store $store1")
}

fun test3() {
    for (i in 0..10)
        println(i)
    for (i in 0..10 step 2)
        println(i)
    for (i in 10 downTo 0 step 2)
        println(i)
}

fun test4() {
    val p = Person()
    p.name = "Jack"
    p.age = 19
    p.eat()
}

fun test5() {
    val sno = "19"
    val grade = 6
    val name = "Jack"
    val age = 19
    val s = Student(sno, grade)
    val s1 = Student1(sno, grade, name, age)
    val s2 = Student1()
    val s3 = Student1(name, age)
    val s4 = Student2(name, age)
}

fun test6() {
    val s = Student3("Jack", 19)
    doStudy(s)
}

fun doStudy(Study: Study) {
    Study.readBooks()
    Study.doHomeWork()
}

fun doStudy1(Study: Study?) {
    Study?.let {
        it.readBooks()
        it.doHomeWork()
    }
}

fun test7() {
    val cellPhone1 = CellPhone("IPHONE", 2000.00)
    val cellPhone2 = CellPhone("Sansung", 2000.00)
    val cellPhone3 = CellPhone("Sansung", 2000.00)
    println(cellPhone1)
    println(cellPhone2)
    println(cellPhone3)
    println("CellPhone1 equals CellPhone2:${cellPhone1 == cellPhone2}")
    println("CellPhone2 equals CellPhone3:${cellPhone3 == cellPhone2}")
}

fun test8() {
    Singleton.SingletonTest()
}

fun test9() {
    val list = listOf<String>("Apple", "banana") //不可再变化
    for (fruit in list)
        println(fruit)
    println()

    val list1 = mutableListOf("Apple", "banana")
    list1.add("watermellon")
    for (fruit in list1)
        println(fruit)
    println()

    val set = setOf("Apple", "banana") //使用hashset，不一定有序
    for (fruit in set)
        println(fruit)
    println()

    val map = mapOf("Apple" to 1, "Banana" to 2)
    for ((fruit, numbet) in map)
        println("fruit is $fruit,number is $numbet")
}

fun test10() {
    val list = listOf("Apple", "banana", "watermellon")
    val maxlengthFruit = list.maxBy { it.length }
    println(maxlengthFruit)
    println()

    var newList = list.map { it.uppercase(Locale.ROOT) }
    for (fruit in newList)
        println(fruit)
    println()

    newList = list.filter { it.length <= 5 }.map { it.uppercase() } //先调用filter在调用map效率更高
    for (fruit in newList)
        println(fruit)
    println()

    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
    println("anyResult is $anyResult,allResult is $allResult")
    println()

    Thread {
        println("Thread is running")
    }.start()
}

fun main() {
    test10()
}