package com.example.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(countReserved:Int):ViewModel() {
    val count:LiveData<Int>
        get() = _count

    private val _count=MutableLiveData<Int>()

    init {
        _count.value=countReserved
    }

    fun plusOne(){
        val count1=_count.value?:0
        _count.value=count1+1
    }

    fun clear(){
        _count.value=0
    }
}