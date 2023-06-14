package com.example.netblinkapp

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.netblinkapp.DailyActivityEntity

@Dao
interface DailyActivityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(DailyActivity: DailyActivityEntity)

    @Update
    fun update(DailyActivity: DailyActivityEntity)

    @Query("DELETE FROM DailyActivityEntity WHERE id = :DailyActivityId")
    fun deleteById(DailyActivityId: Int)

    @Query("SELECT * from DailyActivityEntity WHERE isDone = 0 ORDER BY timeStart ASC")
    fun getAllDailyActivity(): LiveData<List<DailyActivityEntity>>

    @Query("SELECT * from DailyActivityEntity WHERE isDone = 1 ORDER BY timeStart ASC")
    fun getAllDoneDailyActivity(): LiveData<List<DailyActivityEntity>>

//    @Query("SELECT * from DailyActivityEntity WHERE id = :DailyActivityId")
//    fun getDailyActivityById(DailyActivityId: Int)
}