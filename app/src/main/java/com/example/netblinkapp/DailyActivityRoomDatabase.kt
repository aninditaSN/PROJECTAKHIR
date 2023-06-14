package com.example.netblinkapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DailyActivityEntity::class], version = 1)
abstract class DailyActivityRoomDatabase : RoomDatabase() {
    abstract fun DailyActivityDao(): DailyActivityDao

    companion object {
        @Volatile
        private var INSTANCE: DailyActivityRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): DailyActivityRoomDatabase {
            if (INSTANCE == null) {
                synchronized(DailyActivityRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DailyActivityRoomDatabase::class.java, "dailyactivity_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as DailyActivityRoomDatabase
        }
    }
}