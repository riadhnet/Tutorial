package com.riadh.tutorial.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.riadh.tutorial.room.model.MyEntity

@Database(entities = [MyEntity::class], version = 1)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}