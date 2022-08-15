package com.example.lesttalk.databasse.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Conversas")
data class Talking(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ToUser")
    val ToUser: String,

    @ColumnInfo(name = "FromUser")
    val FromUser: String,

    @ColumnInfo(name = "Time")
    val timestamp: String,

    @ColumnInfo(name = "Message")
    val text : String
)
