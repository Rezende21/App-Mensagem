package com.example.lesttalk.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lesttalk.R
import com.example.lesttalk.application.TalkApplication
import com.example.lesttalk.databinding.FragmentMessageBinding
import com.example.lesttalk.viewmodel.LetsTalkViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MessageFragment : Fragment() {

    private val binding by lazy { FragmentMessageBinding.inflate(layoutInflater) }
    private lateinit var db : FirebaseAuth
    private val viewmodel by lazy { ViewModelProvider(requireActivity(), LetsTalkViewModel.Factory(
        TalkApplication.getInstance()
    ))[LetsTalkViewModel::class.java]}
    //private var db2 = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        db = Firebase.auth

        findUser()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun findUser() {
        val currentUser = db.currentUser
        if (currentUser == null) {
            findNavController().navigate(R.id.action_messageFragment_to_loginFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.sair -> {
                db.signOut()
                findNavController().navigate(R.id.action_messageFragment_to_loginFragment)
                return true
            }
            R.id.contatos -> {
                findNavController().navigate(R.id.action_messageFragment_to_contactsFragment)
                return true
            }
            R.id.configuração -> {
                updateCurrentUser()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateCurrentUser() {
        val bundle = Bundle()
        bundle.putString("CurrentUser", db.currentUser?.uid)
        Log.i("usuario atual", db.currentUser?.uid.toString())
        findNavController().navigate(R.id.action_messageFragment_to_configFragment, bundle)
    }

}