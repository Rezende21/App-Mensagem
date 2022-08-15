package com.example.lesttalk.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.Navigator
import com.example.lesttalk.databasse.TalkDatabase
import com.example.lesttalk.databasse.entities.Talking
import com.example.lesttalk.repository.TalkRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.jar.Attributes

class LetsTalkViewModel(application : Application) : AndroidViewModel(application) {

    private val repository = TalkRepository(TalkDatabase.getDatabase(application))

    fun insettalk(talking: Talking) {
        viewModelScope.launch {
            repository.inserttalk(talking)
        }
    }

    fun insertMessage(text : String, idTotalk : String) {
        viewModelScope.launch {
            repository.insertTalkingAndMessage(text, idTotalk)
        }
    }

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LetsTalkViewModel::class.java)) {
                return LetsTalkViewModel(application) as T
            }
            throw IllegalArgumentException("Unale to construct TalkViewModel")
        }
    }

}