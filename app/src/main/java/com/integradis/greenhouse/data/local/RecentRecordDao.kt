package com.integradis.greenhouse.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.integradis.greenhouse.data.model.RecentRecord


@Dao
interface RecentRecordDao {
//    @Query("SELECT * FROM recent_records WHERE cropId = :cropId AND phase = :phase AND id = :id")
//    fun getRecentRecord(cropId: Int, phase: String, id: Int): LiveData<RecentRecord>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: RecentRecord)

    @Query("DELETE FROM recent_records WHERE cropId = :cropId AND phase = :phase")
    suspend fun deletePreviousRecords(cropId: String, phase: String)

    @Query("SELECT * FROM recent_records WHERE cropId = :cropId AND phase = :phase LIMIT 1")
    fun getMostRecentRecord(cropId: String, phase: String): LiveData<RecentRecord>
}