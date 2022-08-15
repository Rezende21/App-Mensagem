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
import com.example.lesttalk.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    private lateinit var db : FirebaseAuth
    private var saveUser = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        db = Firebase.auth
        initializeView()
        return binding.root
    }

    private fun initializeView() {
        binding.btCadastro.setOnClickListener {
            createNewUser()
        }
    }

    private fun createNewUser() {
        val getName = binding.editNomeRegister.editText?.text.toString()
        val getLogin = binding.editEmail.editText?.text.toString()
        val getPassword = binding.editPasswordRegister.editText?.text.toString()
        if (getName.isEmpty() || getName.isBlank() || getLogin.isBlank() || getLogin.isEmpty() || getPassword.isBlank() || getPassword.isEmpty()) {
            Toast.makeText(requireContext(), "Porfavor preencha todos os campos!!", Toast.LENGTH_LONG).show()
        } else {
                createTheUser(getName, getLogin, getPassword)
        }
    }

    private fun createTheUser(name : String, email: String, password: String){
        db.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val creatId = FirebaseAuth.getInstance().uid
                    val user = hashMapOf(
                        "uid" to creatId,
                        "Name" to name,
                        "Email" to email,
                        "Password" to password
                    )
                    if (creatId != null) {
                        saveUser.collection("users")
                            .document(creatId)
                            .set(user)
                            .addOnSuccessListener {
                                Log.i("Cadastro feito", "Usuario criado com sucesso")
                                findNavController().navigate(R.id.action_cadastroFragment_to_messageFragment)
                            }
                            .addOnFailureListener {
                                Toast.makeText(requireContext(), "Erro ao criar usuario", Toast.LENGTH_LONG).show()
                                Log.i("User Errado", it.message.toString())
                                //TODO implementar um sistema de erro melhor
                            }
                    }

                }else {
                    Log.i("Deu erro", task.exception.toString())
                    Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_LONG).show()
                }
        }
    }

}