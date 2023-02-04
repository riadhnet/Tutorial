package com.riadh.tutorial.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.riadh.tutorial.room.model.MyEntity

@Dao
interface MyDao {
    @Insert
    fun insert(myEntity: MyEntity)

    @Query("SELECT * FROM my_entity")
    fun getAll(): List<MyEntity>

    @Query("SELECT * FROM my_entity WHERE id = :id")
    fun findById(id: Long): MyEntity

    @Update
    fun update(myEntity: MyEntity)

    @Delete
    fun delete(myEntity: MyEntity)
}