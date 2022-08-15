package com.example.lesttalk.databasse.entities.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.lesttalk.databasse.entities.Messagem
import com.example.lesttalk.databasse.entities.Talking

data class TalkingWithMessagem(
    @Embedded val talking: Talking,
    @Relation(
        parentColumn = "text",
        entityColumn = "text"
    )
    val messagem: List<Messagem>
)
