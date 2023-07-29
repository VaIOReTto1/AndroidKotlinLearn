package com.example.jetpacktest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User):Long

    @Update
    fun updateUser(user: User)

    @Query("select * from User")
    fun loadAllUsers():List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName=:lastName")
    fun deleteUserByLastName(lastName:String):Int
}