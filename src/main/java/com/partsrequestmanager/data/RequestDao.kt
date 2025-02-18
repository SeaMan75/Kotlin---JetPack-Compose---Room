package com.partsrequestmanager.data

// app/data/RequestDao.kt
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RequestDao {
    @Insert
    suspend fun addRequest(request: Request)

    @Query("SELECT * FROM requests")
    suspend fun getAllRequests(): List<Request>
}
