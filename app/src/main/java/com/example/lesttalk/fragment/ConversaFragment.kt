package com.example.lesttalk.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lesttalk.R
import com.example.lesttalk.adapter.MessageAdapter
import com.example.lesttalk.application.TalkApplication
import com.example.lesttalk.databasse.entities.Talking
import com.example.lesttalk.databinding.FragmentConversaBinding
import com.example.lesttalk.viewmodel.LetsTalkViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ConversaFragment : Fragment() {

    private val binding by lazy { FragmentConversaBinding.inflate(layoutInflater)}
    private val viewmodel by lazy { ViewModelProvider(requireActivity(), LetsTalkViewModel.Factory(
        TalkApplication.getInstance()
    ))[LetsTalkViewModel::class.java]}
    private lateinit var dbAuth : FirebaseAuth
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dbAuth = Firebase.auth
        val idtotalk = arguments?.getString("id")
        initView(idtotalk)
        sendMessageToUser(idtotalk)
        getContextTomessage(idtotalk)
        return binding.root
    }

    private fun getContextTomessage(idtotalk: String?) {
        val toUser = idtotalk.toString()
        val fromUser = dbAuth.currentUser?.uid.toString()
        getMessagemtoUser(toUser,fromUser)
    }

    private fun getMessagemtoUser(toUser : String, fromUser : String) {
//        val messageList = mutableListOf<String>()
//        db.collection("menssage")
//            .document(toUser)
//            .collection(fromUser)
//            .addSnapshotListener { value, error ->
//                if (value != null) {
//                    for (i in value) {
//                        messageList += i.data["text"].toString()
//                    }
//                    val messageAdapter by lazy { MessageAdapter(messageList) }
//                    binding.rectcleviewTalk.adapter = messageAdapter
//                    if (error != null) {
//                        Log.e("carregamento menssgem", error.message.toString())
//                    }
//                }
//            }
    }


    private fun initView(id : String?) {
        binding.toolbarConversa.setOnClickListener {
            activity?.onBackPressed()
        }
        db.collection("users").document("$id")
            .get()
            .addOnSuccessListener {
                if (it != null) {
                    binding.toolbarConversa.title = it.data?.get("Name").toString()
                } else {
                    Toast.makeText(requireContext(), "Não foi encontrado usuario", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_conversaFragment_to_messageFragment)
                }
            }
            .addOnFailureListener {
                Log.i("conversaFragment", it.message.toString())
                //TODO implementar um sistema de erro melhor
            }
    }

    private fun sendMessageToUser(idtotalk: String?) {
        binding.btFloat.setOnClickListener {
            sendMessage(idtotalk)
        }
    }

    private fun sendMessage(idtotalk: String?) {
        val fromUser = dbAuth.currentUser?.uid.toString()
        val timestamp = System.currentTimeMillis()
        val text = binding.editMessage.text.toString()
        if (text != "") {
           // viewmodel.insertMessage(text, idtotalk.toString())
            binding.editMessage.editableText.clear()
            val talking = Talking(
                idtotalk.toString(),
                fromUser,
                timestamp.toString(),
                text
            )
            viewmodel.insettalk(talking)
        }

    }

}