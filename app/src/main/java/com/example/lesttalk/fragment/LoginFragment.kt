package com.example.lesttalk.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lesttalk.R
import com.example.lesttalk.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private lateinit var db : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        db = Firebase.auth
        initiazeView()
        return binding.root
    }

    private fun initiazeView() {
        binding.txtLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
        }

        binding.btLogin.setOnClickListener {
            val getEmail = binding.editLogin.editText?.text.toString()
            val getPassword = binding.editSenha.editText?.text.toString()
            if (getEmail.isBlank() || getEmail.isEmpty() || getPassword.isBlank() || getPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Porfavor preencha todos os campos!!", Toast.LENGTH_LONG).show()
            } else {
                loginWithTheUser(getEmail, getPassword)
            }
        }
    }

    private fun loginWithTheUser(email: String, password: String) {
        db.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment_to_messageFragment)
                } else {
                    Log.i("Login errado", it.exception.toString())
                }
            }
    }


}