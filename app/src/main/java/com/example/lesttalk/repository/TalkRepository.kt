package com.example.lesttalk.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.lesttalk.adapter.MessageAdapter
import com.example.lesttalk.databasse.entities.Talking
import com.example.lesttalk.databasse.TalkDatabase
import com.example.lesttalk.databasse.entities.Messagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TalkRepository(private val database : TalkDatabase) {

    private val db = Firebase.firestore
    private var dbAuth = Firebase.auth

    suspend fun getParametersToSaveOnRoom(text: String, idtotalk : String) {
//        val timestamp = System.currentTimeMillis()
//        val fromUser = dbAuth.currentUser?.uid.toString()
//        val talking = Talking(
//            idtotalk,
//            fromUser,
//            "$timestamp",
//            text
//        )
//        val messagem = Messagem(
//            text,
//            "$timestamp"
//        )
//        salvarAllthings(talking, messagem)
//        insertTalkingAndMessage(talking, messagem)
//        getTalksAndMessage(talking)
    }

    suspend fun inserttalk(talking: Talking) {
        database.talkDao.insertTalk(talking)
    }

    suspend fun insertTalkingAndMessage(text: String, idtotalk: String) {
        val timestamp = System.currentTimeMillis()
        val fromUser = dbAuth.currentUser?.uid.toString()
        val talking = Talking(
            idtotalk,
            fromUser,
            "$timestamp",
            text
        )
        val messagem = Messagem(
            text,
            "$timestamp"
        )
        database.talkDao.insertTalk(talking)
        database.talkDao.inserMessage(messagem)
        db.collection("menssage")
            .document(talking.ToUser)
            .collection(talking.FromUser)
            .add(messagem)
            .addOnSuccessListener {
                Log.i("Envio Message", it.id)
            }
            .addOnFailureListener {
                Log.e("Envio Message", it.message.toString())
            }
                // esse resto de codigo manda a msg de uma forma inversa do codigo acima
        db.collection("menssage")
            .document(talking.FromUser)
            .collection(talking.ToUser)
            .add(messagem)
            .addOnSuccessListener {
                Log.i("Envio Message", it.id)
            }
            .addOnFailureListener {
                Log.e("Envio Message", it.message.toString())
            }
    }

    suspend fun getTalksAndMessage(talking: Talking){
        db.collection("menssage")
            .document(talking.ToUser)
            .collection(talking.FromUser)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    for (i in value) {
                        val talking = Talking(
                            i.id,
                            i.id,
                            i.data["timemessagestamp"].toString(),
                            i.data["text"].toString()
                        )
                        Log.i("Repository", i.data["text"].toString())
                        val messagem = Messagem(
                            i.data["text"].toString(),
                            i.data["timemessagestamp"].toString()
                        )
                        suspend {salvarAllthings(talking, messagem)}
                    }
                    if (error != null) {
                        Log.e("carregamento menssgem", error.message.toString())
                    }
                }
            }
    }

    suspend fun salvarAllthings(talking: Talking, messagem: Messagem) {
        database.talkDao.insertTalk(talking)
        database.talkDao.inserMessage(messagem)
    }
}