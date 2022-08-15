package com.example.lesttalk.databasse

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lesttalk.databasse.entities.Messagem
import com.example.lesttalk.databasse.entities.Talking

@Dao
interface TalkDoa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTalk(talking: Talking)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserMessage(messagem: Messagem)

}
