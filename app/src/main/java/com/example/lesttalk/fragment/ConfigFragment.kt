package com.example.lesttalk.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.lesttalk.R
import com.example.lesttalk.databinding.FragmentConfigBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ConfigFragment : Fragment() {

    private val binding by lazy { FragmentConfigBinding.inflate(layoutInflater)}
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getTheViewtoInit()
        updateUser()
        return binding.root
    }

    private fun getTheViewtoInit() {
        val getUserFromMessageFragment = arguments?.getString("CurrentUser")
        db.collection("users").document("$getUserFromMessageFragment")
            .get()
            .addOnSuccessListener {
                if (it != null) {
                    binding.txtEmailConfiguraOes.text = it.data?.get("Email").toString()
                    binding.txtNomeConfiguraEs.text = it.data?.get("Name").toString()
                } else {
                    Toast.makeText(requireContext(), "nada encontrado", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Erro ao carregar resultados", Toast.LENGTH_LONG).show()
                Log.i("Erro", it.message.toString())
                //TODO implementar um sistema de erro melhor
            }
        binding.toobarConfig.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun updateUser() {
        //TODO Sistema de update de dados
    }

}