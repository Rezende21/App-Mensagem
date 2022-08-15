package com.example.lesttalk.databasse.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Menssagem")
data class Messagem(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "text")
    var text: String,

    @ColumnInfo(name = "timemessagestamp")
    var timemessagestamp : String
)
