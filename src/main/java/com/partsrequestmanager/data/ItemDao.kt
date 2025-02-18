package com.partsrequestmanager.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// app/data/ItemDao.kt
@Dao
interface ItemDao {
    @Insert
    suspend fun addItem(item: Item)

    @Query("SELECT * FROM items WHERE name IN (:names)")
    suspend fun getItemsByNames(names: List<String>): List<Item>
}