package com.example.lesttalk.databasse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lesttalk.databasse.entities.Messagem
import com.example.lesttalk.databasse.entities.Talking

@Database(entities =
    [Talking::class,
    Messagem::class],
    version = 6, exportSchema = false)
abstract class TalkDatabase : RoomDatabase(){

    abstract val talkDao : TalkDoa

    companion object {
        @Volatile
        private var INSTANCE : TalkDatabase? = null

        fun getDatabase(context: Context) : TalkDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TalkDatabase::class.java,
                    "Lest_Talk.db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }


}