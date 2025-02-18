package com.partsrequestmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests")
data class Request(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String, // Тип работы (Промывка отопления, замена насоса...)
    val description: String, // Описание
    val items: String // Необходимые предметы (через запятую)
)
