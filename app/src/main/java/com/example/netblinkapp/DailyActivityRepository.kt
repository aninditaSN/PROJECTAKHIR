package com.example.netblinkapp

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DailyActivityRepository(application: Application) {
    private val mDailyActivtyDao: DailyActivityDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = DailyActivityRoomDatabase.getDatabase(application)
        mDailyActivtyDao = db.DailyActivityDao()
    }


    fun insert(dailyActivity: DailyActivityEntity) {
        executorService.execute { mDailyActivtyDao.insert(dailyActivity) }
    }

    fun update(dailyActivity: DailyActivityEntity) {
        executorService.execute { mDailyActivtyDao.update(dailyActivity) }
    }

    fun deleteDailyActivityById(Id: Int) {
        mDailyActivtyDao.deleteById(Id)
    }

    fun getAllDailyActivity(): LiveData<List<DailyActivityEntity>> =
        mDailyActivtyDao.getAllDailyActivity()

    fun getAllArchiveDailyActivity(): LiveData<List<DailyActivityEntity>> =
        mDailyActivtyDao.getAllDoneDailyActivity()

//    fun getDailyActivityById(id:Int): LiveData<List<DailyActivityEntity>> =
//        mDailyActivtyDao.getDailyActivityById(id)

}
