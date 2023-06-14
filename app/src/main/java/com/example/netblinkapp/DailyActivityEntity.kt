package com.example.netblinkapp

import androidx.room.*
import com.example.netblinkapp.DailyActivityEntity
@Entity()
data class DailyActivityEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "timeStart")
    var timeStart: String? = null,

    @ColumnInfo(name = "timeEnd")
    var timeEnd: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "desc")
    var desc: String? = null,

    @ColumnInfo(name = "isDone")
    var isDone: Boolean = false
)