package com.partsrequestmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String, // Название предмета
    val size: String, // Размер или спецификация
    val description: String // Описание использования
)