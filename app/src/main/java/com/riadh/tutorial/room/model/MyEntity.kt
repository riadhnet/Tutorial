package com.riadh.tutorial.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_entity")
data class MyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val age: Int
)