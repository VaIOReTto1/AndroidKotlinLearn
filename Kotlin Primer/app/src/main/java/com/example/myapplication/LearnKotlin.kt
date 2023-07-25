package com.example.myapplication

import java.lang.StringBuilder
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

fun test11() {
    val list = listOf("Apple", "banana", "watermellon")
    val builder = StringBuilder()
    builder.append("Start eating fruit.\n")
    for (fruit in list)
        builder.append(fruit).append("\n")
    builder.append("Ate all fruits.")
    val result = builder.toString()
    println(result)
    println()

    val result1 = with(StringBuilder()) {
        append("Start eating fruit.\n")
        for (fruit in list)
            append(fruit).append("\n")
        append("Ate all fruits.")
        toString()
    }
    println(result1)
    println()

    val result2 = StringBuilder().run {
        append("Start eating fruit.\n")
        for (fruit in list)
            append(fruit).append("\n")
        append("Ate all fruits.")
        toString()
    }
    println(result2)
    println()

    val result3 = StringBuilder().apply {
        append("Start eating fruit.\n")
        for (fruit in list)
            append(fruit).append("\n")
        append("Ate all fruits.")
        //无返回值
    }
    println(result3.toString())
}

fun String.letterCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter())
            count++
    }
    return count
}

fun test12() {
    val count = "shedashd##45".letterCount()
    println(count)
}

class Money(val value: Int) {
    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }

    operator fun plus(newValue: Int): Money {
        val sum = value + newValue
        return Money(sum)
    }
}

fun test13() {
    val money1 = Money(10)
    val money2 = Money(5)
    val money3 = money1 + money2
    println(money3.value)
    val money4 = money3 + 5
    println(money4.value)
}

inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}

fun StringBuilder.build(block:StringBuilder.()->Unit):StringBuilder{
    block()
    return this
}

fun printString(string: String,block: (String) -> Unit){
    println("printString begin")
    block(string)
    println("printString end")
}

inline fun printString1(string: String,block: (String) -> Unit){
    println("printString begin")
    block(string)
    println("printString end")
}

fun HigherOrderFuction() {
//    val num1 = 20
//    val num2 = 50
////    val result1= num1AndNum2(num1,num2,::plus)
////    val result2= num1AndNum2(num1,num2,::minus)
//    val result1 = num1AndNum2(num1, num2) { n1, n2 -> n1 + n2 }
//    val result2 = num1AndNum2(num1, num2) { n1, n2 -> n1 - n2 }
//    println("result1 is $result1")
//    println("result2 is $result2")

//    val list= listOf("Apple","Banana","Pear","Orange")
//    //形成与apply同样的效果
//    val result=StringBuilder().build {
//        append("Start eating fruits.\n")
//        for (fruit in list)
//            append(fruit).append("\n")
//        append("Ate all fruits")
//    }
//    println(result.toString())

    val str=""
    //只是局部返回
    printString(str){s->
        println("Lambda start")
        if(s.isEmpty()) return@printString
        println(s)
        println("Lambda end")
    }
    println()

    printString1(str){s->
        println("Lambda start")
        if(s.isEmpty()) return
        println(s)
        println("Lambda end")
    }
}

fun <T> T.build(block:T.() -> Unit):T{
    block()
    return this
}

class Myset<T>(val helperSet:HashSet<T>):Set<T> by helperSet{
    fun helloWorld()= println("Hello World")
}

fun main() {
    HigherOrderFuction()
}